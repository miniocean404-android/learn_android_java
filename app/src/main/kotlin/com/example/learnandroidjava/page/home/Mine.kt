package com.example.learnandroidjava.page.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.learnandroidjava.component.common.custom_app_bar.CustomTopAppBar


@Composable
fun MinePage(navController: NavController) {
    CustomTopAppBar() {
        Text(text = "我的")
    }
    Text(text = "我得")
}


@Preview(showBackground = true)
@Composable
fun MinePagePreview() {
}