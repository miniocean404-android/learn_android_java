package com.example.learnandroidjava.project.component.learn

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun GetScreenDp(){
    // 获取屏幕宽度 dp
    val dp = with(LocalConfiguration.current) {
        screenWidthDp
    }

    Text(text = "获取屏幕宽度 dp: $dp")
}