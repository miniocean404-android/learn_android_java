package com.example.learnandroidjava.extension

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow

/**
 * @param buffer 距离底部多少条开始执行 load 函数
 */
@Composable
fun LazyListState.OnBottomReached(buffer: Int = 1, load: () -> Unit) {
    // 报错提示
    require(buffer >= 0) { "buffer 必须大于等于 0" }

    val isLoad = remember {
        // 由另一个计算状态派生
        derivedStateOf {
            // 获取最后显示的 item
            val lastVisit = layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            // 如果获取的 item 是最后一个 item 代表已经触底
            lastVisit.index == layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(isLoad) {
        // snapshotFlow 观察 isLoad 的值是否改变，改变后执行 load
        snapshotFlow { isLoad.value }.collect {
            if (it) {
                load()
            }
        }
    }
}