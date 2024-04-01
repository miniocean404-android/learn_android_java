package com.example.learnandroidjava.project.page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.learnandroidjava.project.component.CustomTopAppBar
import com.example.learnandroidjava.project.modal.entity.NavigationItem

@Composable
fun TaskPage(statusBarHeight: Int) {
    CustomTopAppBar(statusBarHeight) {
        Text(text = "任务")
    }
    Text(text = "Task")
}


@Preview(showBackground = true)
@Composable
fun TaskPagePreview() {
}