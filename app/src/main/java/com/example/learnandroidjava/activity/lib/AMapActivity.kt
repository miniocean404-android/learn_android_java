package com.example.learnandroidjava.activity.lib

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.MapView
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MarkerOptions
import com.amap.api.maps.model.MyLocationStyle
import com.example.learnandroidjava.databinding.ActivityAmapBinding
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hjq.toast.Toaster
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * 文章：https://blog.csdn.net/Qinbenjiaren/article/details/130743437
 * https://juejin.cn/post/7087286815807242254
 */
class AMapActivity : AppCompatActivity() {
    private val TAG: String? = AMapActivity::class.simpleName

    private val bind: ActivityAmapBinding by lazy {
        ActivityAmapBinding.inflate(layoutInflater)
    }

    private var mMapView: MapView? = null
    private var aMap: AMap? = null
    private var mLocationClient: AMapLocationClient? = null

    private var lat = 0.0
    private var lon = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        getSha1(this)

        initAMap()
        // 创建地图
        bind.amap.onCreate(savedInstanceState)

        // 必须创建地图后调用，isMyLocationEnabled 是 null
        getPermissions(onSuccess = {
            // 是否显示定位蓝点，但是开启会有定位, 当它为 true 时，地图会自动定位到当前位置
            aMap?.isMyLocationEnabled = true
        })
    }

    private fun initAMap() {
        // 必须初始化地图前调用
        setAMapPrivacy()

        mMapView = bind.amap
        if (aMap == null) {
            aMap = mMapView!!.map
        }

        try {
            mLocationClient = AMapLocationClient(this@AMapActivity)
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
        }

        // 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap?.myLocationStyle = MyLocationStyle()
            // 设置定位蓝点的 Style
            .myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)
            //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
            .interval(1000)
            // 是否显示定位蓝点
            .showMyLocation(true)


        // 设置默认定位按钮是否显示，非必需设置。
        // 可通过mUiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_BUTTOM)调整缩放控件位置
        aMap?.uiSettings?.isMyLocationButtonEnabled = true
        aMap?.uiSettings?.isCompassEnabled = true// 指南针
        aMap?.uiSettings?.isScaleControlsEnabled = true// 比例尺
        aMap?.uiSettings?.isZoomControlsEnabled = true// 缩放控件
        aMap?.uiSettings?.isScrollGesturesEnabled = true// 滑动
        aMap?.uiSettings?.isZoomGesturesEnabled = true// 缩放
        aMap?.uiSettings?.isTiltGesturesEnabled = true// 倾斜
        aMap?.uiSettings?.isRotateGesturesEnabled = true// 旋转
        aMap?.uiSettings?.setAllGesturesEnabled(true) // 所有手势的开关

        // 设置地图的缩放级别
        aMap?.moveCamera(CameraUpdateFactory.zoomTo(15f))
    }

    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView!!.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 在 activity 执行 onSaveInstanceState 时执行 mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView!!.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 在activity执行 onDestroy 时执行 mMapView.onDestroy()，销毁地图
        mMapView!!.onDestroy()
        mLocationClient?.onDestroy()
    }

    private fun setAMapPrivacy() {
        // 隐私协议更新
        AMapLocationClient.updatePrivacyShow(this, true, true)
        AMapLocationClient.updatePrivacyAgree(this, true)
    }


    fun onLocationChanged(aMapLocation: AMapLocation?) {
        Log.i("aMapLocation：pcw", "lat : $aMapLocation")

        if (aMapLocation != null) {
            if (aMapLocation.errorCode == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.locationType //获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.latitude //获取纬度
                aMapLocation.longitude //获取经度
                aMapLocation.accuracy //获取精度信息
                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = Date(aMapLocation.time)
                df.format(date) //定位时间
                aMapLocation.address //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.country //国家信息
                aMapLocation.province //省信息
                aMapLocation.city //城市信息
                aMapLocation.district //城区信息
                aMapLocation.street //街道信息
                aMapLocation.streetNum //街道门牌号信息
                aMapLocation.cityCode //城市编码
                aMapLocation.adCode //地区编码
                aMapLocation.aoiName //获取当前定位点的AOI信息
                lat = aMapLocation.latitude
                lon = aMapLocation.longitude
                Log.i("经纬度：pcw", "lat : $lat lon : $lon")

                // 设置当前地图显示为当前位置
                aMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lon), 15f))
                val markerOptions = MarkerOptions()
                markerOptions.position(LatLng(lat, lon))
                markerOptions.title("当前位置")
                markerOptions.visible(true)

//                    val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(
//                        BitmapFactory.decodeResource(
//                            getResources(),
//                            R.drawable.ic_launcher_background
//                        )
//                    )
//                    markerOptions.icon(bitmapDescriptor)
                aMap!!.addMarker(markerOptions)
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Toast.makeText(
                    this@AMapActivity,
                    aMapLocation.errorCode.toString() + ", errInfo:"
                            + aMapLocation.errorInfo, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun getPermissions(onSuccess: () -> Unit) {
        XXPermissions.with(this)
            .permission(Permission.ACCESS_FINE_LOCATION)
            .permission(Permission.ACCESS_COARSE_LOCATION)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        Toaster.show("获取部分权限成功，但部分权限未正常授予")
                        return
                    }
                    Toaster.show("获取权限成功")
                    onSuccess()
                }

                override fun onDenied(permissions: List<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        Toaster.show("被永久拒绝授权，请手动授予权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(this@AMapActivity, permissions)
                    } else {
                        Toaster.show("获取权限失败")
                    }
                }
            })
    }

    // 获取 key 时候填写的 sha1
    private fun getSha1(context: Context): String {
        try {
            val info = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNATURES
            )
            val cert = info.signatures[0].toByteArray()
            val md = MessageDigest.getInstance("SHA1")
            val publicKey = md.digest(cert)
            val hexString = StringBuffer()

            for (i in publicKey.indices) {
                val appendString = Integer.toHexString(0xFF and publicKey[i].toInt())
                    .uppercase(Locale.US)
                if (appendString.length == 1)
                    hexString.append("0")
                hexString.append(appendString)
                hexString.append(":")
            }

            val result = hexString.toString()

            Log.i(TAG, "SHA1: ${result.substring(0, result.length - 1)}")
            return result.substring(0, result.length - 1)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}