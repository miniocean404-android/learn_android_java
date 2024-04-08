package com.example.learnandroidjava.page.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.learnandroidjava.component.common.custom_app_bar.CustomTopAppBar
import com.example.learnandroidjava.navigation.Router

@Composable
fun TaskPage(navController: NavController) {
    Column {
        CustomTopAppBar() {
            Text(text = "任务")
        }

        Button(onClick = { navController.navigate(Router.WEB_VIEW)}) {
            Text(text = "跳转 web view")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskPagePreview() {
}