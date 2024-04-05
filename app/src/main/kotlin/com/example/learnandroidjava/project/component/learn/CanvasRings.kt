package com.example.learnandroidjava.project.component.learn

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CanvasRings() {
    val screenHalf = with(LocalConfiguration.current) {
        screenWidthDp / 2
    }

    Canvas(modifier = Modifier.size(screenHalf.dp)) {
        drawArc(
            Color(0,0,0,33),
            startAngle = 160f,
            sweepAngle = 220f,
            // 指示圆弧是否要闭合标志
            useCenter = false,
            // 使用描边绘制内容, cap 指定线帽的形状
            style = Stroke(30f, cap = StrokeCap.Round)
        )

        rotate(
            180f
        ) {
            drawArc(
                Color.Blue,
                startAngle = -20f,
                // 绘画角度
                sweepAngle = 180f,
                // 指示圆弧是否要闭合标志
                useCenter = false,
                // 使用描边绘制内容
                style = Stroke(30f, cap = StrokeCap.Round)
            )
        }
    }
}


@Composable
@Preview
fun CanvasRingsPreview() {
    CanvasRings()
}