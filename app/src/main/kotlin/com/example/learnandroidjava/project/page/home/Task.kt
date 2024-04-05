package com.example.learnandroidjava.project.page.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.learnandroidjava.project.component.common.WebViewComponent
import com.example.learnandroidjava.project.component.common.custom_app_bar.CustomTopAppBar
import com.example.learnandroidjava.project.component.common.rememberWebViewState

@Composable
fun TaskPage() {
    Column {
        CustomTopAppBar() {
            Text(text = "任务")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskPagePreview() {
}