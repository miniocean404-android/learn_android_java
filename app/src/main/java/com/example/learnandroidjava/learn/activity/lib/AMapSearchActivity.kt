package com.example.learnandroidjava.learn.activity.lib

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.Poi
import com.amap.api.navi.AMapNavi
import com.amap.api.navi.AmapNaviPage
import com.amap.api.navi.AmapNaviParams
import com.amap.api.navi.AmapNaviType
import com.amap.api.navi.AmapPageType
import com.amap.api.services.help.Inputtips
import com.amap.api.services.help.InputtipsQuery
import com.amap.api.services.help.Tip
import com.example.learnandroidjava.databinding.ActivityAmapSearchBinding
import com.example.learnandroidjava.learn.shared.adapter.recycler_view.AMapSearchRecyclerAdapter

class AMapSearchActivity : AppCompatActivity(), TextWatcher,
    AMapSearchRecyclerAdapter.OnItemClickListener, Inputtips.InputtipsListener {

    private val binding: ActivityAmapSearchBinding by lazy {
        ActivityAmapSearchBinding.inflate(layoutInflater)
    }
    private val recycleView by lazy {
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler
    }

    // poi 提示词搜索
    private val inputTips: Inputtips by lazy {
        val  tip = Inputtips(this, InputtipsQuery("", ""))
        tip.setInputtipsListener(this)
        tip
    };

    private val adapter by lazy {
        val adapter = AMapSearchRecyclerAdapter(this, recycleView, listOf())
        adapter.setOnItemClickListener(this)
        adapter
    }

    /**
     * 初始化
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.query.addTextChangedListener(this)

        recycleView.adapter = adapter

        // 导航
        AMapNavi.getInstance(this).apply {
            // 设置语音播报
            setUseInnerVoice(true, false)
        }
    }

    /**
     * 输入框内容变化监听
     */
    override fun beforeTextChanged(s: CharSequence?, start: Int, ocunt: Int, after: Int) {
    }

    /**
     * 输入框内容变化监听
     */
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // 设置查询词，查询提示请求
        inputTips.query = InputtipsQuery(s.toString(), "").apply {
            cityLimit = true // 限制在当前城市
        }

        inputTips.requestInputtipsAsyn()
    }

    /**
     * 输入框内容变化监听
     */
    override fun afterTextChanged(p0: Editable?) {
    }


    /**
     * 地图提示请求得到了数据
     */
    override fun onGetInputtips(list: List<Tip>?, resultID: Int) {
        if (list != null) adapter.data = list
    }


    override fun onItemClick(view: View?, position: Int, tip: Tip) {
        val lat = tip.point.latitude
        val lon = tip.point.longitude

        // 创建 poi 对象，传入 兴趣点名称、经纬度、poiID
        val poi = Poi(tip.name, LatLng(lat, lon), tip.poiID)

        // 创建导航参数对象，传入起点、途径点、终点、导航类型、页面类型（计算路线还是直接开始导航）
        val params =
            AmapNaviParams(null, null, poi, AmapNaviType.DRIVER, AmapPageType.ROUTE)


        Log.i("mini_ocean", "onItemClick:  $params ${lat} ${lon} ${tip.name} ${tip.poiID}")

        AmapNaviPage.getInstance().showRouteActivity(applicationContext, params, null)
    }
}