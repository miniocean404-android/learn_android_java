package com.example.learnandroidjava.component.common.webview

sealed class WebViewData() {
    data class Url(val url: String) : WebViewData()
    data class Html(val html: String, val baseUrl: String?) : WebViewData()
}