package com.example.learnandroidjava.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import mini.ocean.tool.ui.theme.ToolTheme

@Composable
fun ListLayout() {
    // 添加可滚动状态才能滚动
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text(text = "Column 元素 $it", style = MaterialTheme.typography.titleSmall)
        }
    }
}

/**
 * LazyColumn 与 Column 区别是：
 *  Column 会直接绘制全部
 *  LazyColumn 只绘制看到的部分
 */
@Composable
fun LazyListLayout() {
    // 添加可滚动状态才能滚动
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(100) {
            Text(text = "LazyColumn 元素 $it", style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
fun ScrollLayout() {
    val scrollState = rememberLazyListState()
    val rememberCoroutineScope = rememberCoroutineScope()
    val size = 100

    Column {
        Row {
            Button(modifier = Modifier.weight(1f), onClick = {
                rememberCoroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text(text = "滚动到顶部")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                rememberCoroutineScope.launch{
                    scrollState.animateScrollToItem(size)
                }
            }) {
                Text(text = "滚动到底部")
            }
        }

        // 添加可滚动状态才能滚动
        LazyColumn(state = scrollState) {
            items(size) {
                Text(text = "ScrollLayout 元素 $it", style = MaterialTheme.typography.titleSmall)
//                Image(painter = rememberAsyncImagePainter(model = "https://picsum.photos/200/300"), contentDescription = null)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListLayoutPreview() {
    ToolTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ListLayout()
        }
    }
}