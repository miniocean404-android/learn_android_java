package com.example.learnandroidjava.project.component.learn

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun CanvasLineChart() {
    val canvasWidth = LocalConfiguration.current.screenWidthDp + 8 * 2

    val point = listOf(0.0, 8.0, 2.0, 5.0, 4.0, 2.0, 9.0)

    val rowHeight = 24
    val totalLine = 5
    // 小圆圈高度
    val circleHeight = 2.5

    val totalHeight = rowHeight * totalLine + circleHeight * 2

    val preY = 8 // 24 * 5 / 15 每 8dp 代表一积分，也就是一行 3 积分

    // canvas 中用的都是 px 单位
    Canvas(modifier = Modifier.size(canvasWidth.dp, totalHeight.dp)) {
        // 画背景横线
        for (i in 0 until totalLine) {
            val y = (i * rowHeight + circleHeight).dp.toPx()
            drawLine(
                start = Offset(0f, y),
                end = Offset(size.width, y),
                color = Color(0xffeeeeee),
                strokeWidth = 2.5f,
            )
        }

        // 画圆圈、折线
        for (i in point.indices) {
            val x = (i * 40).dp.toPx()
            val y = (point[i] * preY).dp.toPx()

            // 画圆圈
            drawCircle(
                color = Color(0xffFFC107),
                radius = circleHeight.dp.toPx(),
                center = Offset(x, y),
                style = Stroke(width = 5f)
            )

            // 画折线
            if (i != 0) {
                val preX = ((i - 1) * 40).dp.toPx()
                val preY = (point[i - 1] * preY).dp.toPx()
                drawLine(
                    start = Offset(preX, preY),
                    end = Offset(x, y),
                    color = Color(0xffFFC107),
                    strokeWidth = 2.5f,
                )
            }
        }
    }

}