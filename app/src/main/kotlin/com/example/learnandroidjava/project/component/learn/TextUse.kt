package com.example.learnandroidjava.project.component.learn

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextUse() {
    Text(buildAnnotatedString {
        append("")
        withStyle(SpanStyle(fontSize = 30.sp)) {
            append("1000")
        }
        withStyle(SpanStyle(fontSize = 12.sp)) {
            append("分")
        }
    }, modifier = Modifier.offset(100.dp, 30.dp))


    val annotationString = buildAnnotatedString {
        append("内容")
        appendInlineContent("icon", "[icon]")
    }

    // 图标与文字混排写法
    val inlineContent = mapOf(
        Pair(
            "icon",
            InlineTextContent(
                Placeholder(
                    width = 16.sp,
                    height = 16.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {
                // 这里的 it 是 alternateText
                Icon(imageVector = Icons.Default.HelpOutline, contentDescription = null)
            }
        ))

    Text(annotationString, inlineContent = inlineContent, fontSize = 14.sp, color = Color.Red)
}

