package com.example.learnandroidjava.project.component.common

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewComponent(state: WebViewState) {
    AndroidView(factory = { context ->
        android.webkit.WebView(context).apply {
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
        }
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
fun rememberWebViewState(url: String) = remember(key1 = url) { WebViewState(WebViewData.Url(url)) }

@Composable
fun rememberWebViewState(html: String, baseUrl: String? = null) =
    remember(key1 = html, key2 = baseUrl) {
        // trimIndent 删除所有空白缩进
        WebViewState(WebViewData.Html(html.trimIndent(), baseUrl))
    }


class WebViewState(webViewData: WebViewData) {
    var title: String? by mutableStateOf(null)
    val data by mutableStateOf(webViewData)

    fun evaluateJavascript(script: String, resultCallback: (String) -> Unit = {}) {
    }
}


sealed class WebViewData() {
    data class Url(val url: String) : WebViewData()
    data class Html(val html: String, val baseUrl: String?) : WebViewData()
}


