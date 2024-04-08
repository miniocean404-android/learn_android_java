package com.example.learnandroidjava.component.learn

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun GetScreenDp() {

    // 获取屏幕宽度
    val screenWidth = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }

    // 获取屏幕高度
    val screenHeight = with(LocalDensity.current) {
        LocalConfiguration.current.screenHeightDp.dp.toPx()
    }

    /**
     * 撑满屏幕方法 1： BoxWithConstraints
     * 撑满屏幕方法 2： LocalConfiguration.current.screenWidthDp
     */
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.background(
                Brush.linearGradient(
                    listOf(
                        Color(0xffbb8378),
                        Color.Transparent
                    ),
                    start = Offset(x = screenWidth, y = 0f),
                    end = Offset(x = 0f, y = screenHeight)

                )
            )
        ) {
            Column {
                Text(text = "获取屏幕宽度 dp: $screenWidth")
                Text(text = "获取屏幕高度 dp: $screenHeight")
            }
        }

        Box(
            modifier = Modifier.background(
                Brush.linearGradient(
                    listOf(
                        Color(0xffbb8378),
                        Color.Transparent
                    ),
                    start = Offset(x = 0f, y = constraints.maxHeight.toFloat()),
                    end = Offset(x = constraints.maxWidth.toFloat(), y = 0f)
                )
            )
        ) {
        }
    }
}