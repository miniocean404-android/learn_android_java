package com.example.learnandroidjava.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * 沉浸式状态栏和导航栏
 *
 * 三方库： ImmersionBar
 * 高版本（当前使用）：https://juejin.cn/post/7203563038301061181#heading-12
 * 兼容低版本：https://juejin.cn/post/7075578574362640421#heading-11
 * 其他：enableEdgeToEdge()、WindowCompat.setDecorFitsSystemWindows(window, false)
 */

// 沉浸式状态栏
fun immerseStatusBar(window: Window) {
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    var systemUiVisibility = window.decorView.systemUiVisibility
    systemUiVisibility =
        systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    window.decorView.systemUiVisibility = systemUiVisibility
    window.statusBarColor = Color.TRANSPARENT

    //设置状态栏文字颜色
    setStatusBarTextColor(window, isSystemDark(window))
}

@SuppressLint("ObsoleteSdkInt")
fun setStatusBarTextColor(window: Window, light: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var systemUiVisibility = window.decorView.systemUiVisibility
        systemUiVisibility = if (light) { //白色文字
            systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        } else { //黑色文字
            systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window.decorView.systemUiVisibility = systemUiVisibility
    }
}

// 沉浸式导航栏
fun immerseNavigationBar(window: Window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // 在Android 10以上，当设置了导航栏栏背景为透明时，isNavigationBarContrastEnforced如果为true，则系统会自动绘制一个半透明背景来提供对比度，所以我们要将这个属性设为false
        window.isNavigationBarContrastEnforced = false
    }

    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    var systemUiVisibility = window.decorView.systemUiVisibility
    systemUiVisibility =
        systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    window.decorView.systemUiVisibility = systemUiVisibility
    window.navigationBarColor = Color.TRANSPARENT

    //设置导航栏按钮或导航条颜色
    setNavigationBarBtnColor(window, isSystemDark(window))
}

fun setNavigationBarBtnColor(window: Window, light: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        var systemUiVisibility = window.decorView.systemUiVisibility

        systemUiVisibility = if (light) { //白色按钮
            systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        } else { //黑色按钮
            systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }

        window.decorView.systemUiVisibility = systemUiVisibility
    }
}


