package com.example.learnandroidjava.project.page

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.learnandroidjava.project.component.custom_app_bar.CustomTopAppBar

@Composable
fun TaskPage() {
    CustomTopAppBar() {
        Text(text = "任务")
    }
    Text(text = "Task")
}


@Preview(showBackground = true)
@Composable
fun TaskPagePreview() {
}