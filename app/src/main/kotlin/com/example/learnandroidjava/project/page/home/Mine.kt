package com.example.learnandroidjava.project.page.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.learnandroidjava.project.component.common.custom_app_bar.CustomTopAppBar


@Composable
fun MinePage() {
    CustomTopAppBar() {
        Text(text = "我的")
    }
    Text(text = "我得")
}


@Preview(showBackground = true)
@Composable
fun MinePagePreview() {
}