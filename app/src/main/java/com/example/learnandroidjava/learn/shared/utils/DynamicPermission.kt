package com.example.learnandroidjava.learn.shared.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


/**
 * Object 创建单例
 */
object DynamicNotice {
    private const val REQUEST_CODE = 1

    @JvmStatic
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun  requestNotificationPermission(activity: Activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_CODE)
        }
    }
}