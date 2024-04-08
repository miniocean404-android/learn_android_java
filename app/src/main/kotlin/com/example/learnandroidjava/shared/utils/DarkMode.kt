package com.example.learnandroidjava.shared.utils

import android.annotation.SuppressLint
import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate

// https://juejin.cn/post/7207347342383382588


@SuppressLint("ServiceCast")
fun isSystemDark(window: Window): Boolean {
    val manger: UiModeManager = window.context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    return manger.nightMode == UiModeManager.MODE_NIGHT_YES
}

// App 应用了什么模式
fun isAppDark(window: Window): Boolean {
    val uiMode = window.context.resources.configuration.uiMode
    return (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}


// 设置为 dark 模式
fun setDark() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
}