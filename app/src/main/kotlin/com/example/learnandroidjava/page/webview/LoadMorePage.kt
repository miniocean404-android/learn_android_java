package com.example.learnandroidjava.page.webview

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.learnandroidjava.extension.OnBottomReached

@Composable
fun LoadMorePage() {
    val lazyListState = rememberLazyListState()
    lazyListState.OnBottomReached {
        Log.i("mini_ocean", "LoadMorePage: 开始加载更多")
    }

    LazyColumn(state = lazyListState) {
        items(100) {
            Text(text = " $it ")
        }
    }
}