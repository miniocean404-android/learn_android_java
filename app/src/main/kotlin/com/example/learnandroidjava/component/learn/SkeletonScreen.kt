package com.example.learnandroidjava.component.learn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun SkeletonScreen() {
    Column {
        Text(
            text = "骨架屏内容",
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
                .placeholder(
                    // 是否显示骨架屏
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.LightGray),
                    color = Color.White
                )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "骨架屏内容",
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
                .placeholder(
                    // 是否显示骨架屏
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.LightGray),
                    color = Color.White
                )
        )
    }
}


@Composable
@Preview
fun SkeletonScreenPreview() {
    SkeletonScreen()
}
