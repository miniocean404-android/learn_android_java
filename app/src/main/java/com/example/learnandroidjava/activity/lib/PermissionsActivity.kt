package com.example.learnandroidjava.activity.lib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.R
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hjq.toast.Toaster

class PermissionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        // AndroidManifest.xml 申请前需要在 android 中添加对应的权限
        XXPermissions.with(this) // 申请单个权限
            .permission(Permission.RECORD_AUDIO) // 申请多个权限
            .permission(*Permission.Group.CALENDAR) // 设置权限请求拦截器（局部设置）
            //.interceptor(new PermissionInterceptor())
            // 设置不触发错误检测机制（局部设置）
            //.unchecked()
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        Toaster.show("获取部分权限成功，但部分权限未正常授予")
                        return
                    }
                    Toaster.show("获取录音和日历权限成功")
                }

                override fun onDenied(permissions: List<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        Toaster.show("被永久拒绝授权，请手动授予录音和日历权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(this@PermissionsActivity, permissions)
                    } else {
                        Toaster.show("获取录音和日历权限失败")
                    }
                }
            })
    }
}