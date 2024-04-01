package com.example.learnandroidjava.project

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.learnandroidjava.project.page.HomePage
import com.example.learnandroidjava.theme.ToolTheme

class App : ComponentActivity() {
    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 方式二：设置状态栏透明
        // 让界面显示在 导航栏和状态栏 后边，也就是导航栏状态栏遮挡住界面
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 方式一：设置状态栏透明(不推荐，已经弃用)
        // window.statusBarColor = Color.Transparent.value.toInt()
        // window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE


        // 获取状态栏高度
        var statusBarHeight = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) statusBarHeight = resources.getDimensionPixelSize(resourceId)

        setContent {
            ToolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage(statusBarHeight)
                }
            }
        }
    }
}