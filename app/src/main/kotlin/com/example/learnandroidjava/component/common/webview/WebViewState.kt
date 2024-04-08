package com.example.learnandroidjava.component.common.webview

import android.webkit.WebView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WebViewState(webViewData: WebViewData, private val coroutineScope: CoroutineScope) {
    var title: String? by mutableStateOf(null)
        internal set

    val data by mutableStateOf(webViewData)

    enum class EventType {
        EVALUATE_JS
    }

    // 共享流的数据类型
    private class Event(val type: EventType, val args: String, val callback: (String) -> Unit)

    // 共享流
    private val events: MutableSharedFlow<Event> = MutableSharedFlow()

    internal suspend fun WebView.handleEvents(): Unit = withContext(Dispatchers.Main) {
        events.collect { event ->
            when (event.type) {
                EventType.EVALUATE_JS -> {
                    evaluateJavascript(event.args, event.callback)
                }
            }
        }
    }

    fun evaluateJavascript(script: String, resultCallback: (String) -> Unit = {}) {
        coroutineScope.launch {
            events.emit(
                Event(
                    EventType.EVALUATE_JS,
                    script,
                    resultCallback
                )
            ) // 发送事件
        }
    }
}

