package com.example.learnandroidjava.component.learn

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnandroidjava.R
import com.example.learnandroidjava.shared.theme.ToolTheme


@Composable
fun MessageCard(
    data: MessageData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(all = 8.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "描述文字",
            modifier = modifier
                .size(55.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = modifier.width(8.dp))

        // 当界面刷新时候 remember 记住变量的值，下次还可以用
        var isExpanded by remember {
            mutableStateOf(false)
        }

        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "",
        )

        Column(modifier = modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = data.auth, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = modifier.height(4.dp))
            Surface(
                // 圆角
                shape = MaterialTheme.shapes.medium,
                // 阴影
                shadowElevation = 10.dp,
                color = surfaceColor,
                modifier = Modifier
                    // 内容尺寸变化动画
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = data.body,
                    modifier = modifier.padding(end = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<MessageData>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

data class MessageData(val auth: String, val body: String)

@Preview(showBackground = true)
@Composable
fun LayoutActivityPreview() {
    ToolTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Conversation(
                listOf(
                    MessageData("Alice", "Hi"),
                    MessageData(
                        "Bob",
                        "Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello" +
                                "Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello"
                    ),
                    MessageData("Bob", "Hello"),
                    MessageData("Bob", "Hello"),
                    MessageData("Bob", "Hello"),
                )
            )
        }
    }
}