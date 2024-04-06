package com.example.learnandroidjava.project.component.common

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewComponent(state: WebViewState) {

    var webView by remember { mutableStateOf<WebView?>(null) }

    // webview 变化或者 state 变化重新订阅流数据
    LaunchedEffect(webView, state) {
        with(state) {
            // 订阅流
            webView?.handleEvents()
        }
    }

    AndroidView(factory = { context ->
        WebView(context).apply {
            // 加载新页面的时候
            webViewClient = object : android.webkit.WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    state.title = ""
                }
            }

            // 获取页面标题
            webChromeClient = object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    state.title = title
                }
            }

            with(settings) {
                javaScriptEnabled = true
//                domStorageEnabled = true
//                useWideViewPort = true
//                loadWithOverviewMode = true
//                setSupportZoom(true)
//                builtInZoomControls = true
//                displayZoomControls = false
//                javaScriptCanOpenWindowsAutomatically = true
//                mediaPlaybackRequiresUserGesture = false
//                allowFileAccess = true
//                allowContentAccess = true
//                allowFileAccessFromFileURLs = true
//                allowUniversalAccessFromFileURLs = true
//                loadsImagesAutomatically = true
//                defaultTextEncodingName = "UTF-8"
//                cacheMode = android.webkit.WebSettings.LOAD_NO_CACHE
//                setGeolocationEnabled(true)
//                setGeolocationDatabasePath(context.filesDir.absolutePath)
//                setSupportMultipleWindows(true)
//                setDatabaseEnabled(true)
//                setDatabasePath(context.filesDir.absolutePath)
//                setRenderPriority(android.webkit.WebSettings.RenderPriority.HIGH)
//                setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
//                setSaveFormData(true)
//                setSavePassword(true)
            }
//            evaluateJavascript()
        }.also { webView = it }
    }) { view ->
        when (val webViewData = state.data) {
            is WebViewData.Url -> {
                if (webViewData.url.isNotEmpty() && webViewData.url != view.url) {
                    view.loadUrl(webViewData.url)
                }
            }

            is WebViewData.Html -> view.loadDataWithBaseURL(
                webViewData.baseUrl,
                webViewData.html,
                "text/html",
                "UTF-8",
                ""
            )
        }
    }
}

@Composable
fun rememberWebViewState(url: String, coroutineScope: CoroutineScope = rememberCoroutineScope()) =
    remember(key1 = url) { WebViewState(WebViewData.Url(url), coroutineScope) }

@Composable
fun rememberWebViewState(
    html: String,
    baseUrl: String? = null,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(key1 = html, key2 = baseUrl) {
        // trimIndent 删除所有空白缩进
        WebViewState(WebViewData.Html(html.trimIndent(), baseUrl), coroutineScope)
    }


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
            events.emit(Event(EventType.EVALUATE_JS, script, resultCallback)) // 发送事件
        }
    }
}


sealed class WebViewData() {
    data class Url(val url: String) : WebViewData()
    data class Html(val html: String, val baseUrl: String?) : WebViewData()
}


