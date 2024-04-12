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
        // 当可组合项输入的变化频率超过您需要的重组频率时，就应该使用 derivedStateOf 函数。这种情况通常是指，某些内容（例如滚动位置）频繁变化，但可组合项只有在超过某个阈值时才需要对其做出响应。
        derivedStateOf {
            // 获取最后显示的 item
            val lastVisit = layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            // 如果获取的 item 是最后一个 item 代表已经触底
            lastVisit.index == layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(isLoad) {
        // snapshotFlow 观察 isLoad 的值是否改变，改变后执行 load
        // 使用 snapshotFlow 将 State<T> 对象转换为冷 Flow。snapshotFlow 会在收集到块时运行该块
        // 并发出从块中读取的 State 对象的结果。当在 snapshotFlow 块中读取的 State 对象之一发生变化时，如果新值与之前发出的值不相等，Flow 会向其 collect 发出新值
        snapshotFlow { isLoad.value }.collect {
            if (it) {
                load()
            }
        }
    }
}