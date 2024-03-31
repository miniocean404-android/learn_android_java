package com.example.learnandroidjava.activity.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.learnandroidjava.component.LazyListLayout
import com.example.learnandroidjava.component.ListLayout
import com.example.learnandroidjava.component.ScrollLayout
import mini.ocean.tool.ui.theme.ToolTheme

class ComposeAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScrollLayout()
                }
            }
        }
    }
}


