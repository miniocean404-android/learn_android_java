package com.example.learnandroidjava.project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.learnandroidjava.project.page.home.HomePage
import com.example.learnandroidjava.project.page.webview.WebViewPage
import com.example.learnandroidjava.theme.ToolTheme
import com.example.learnandroidjava.utils.immerseNavigationBar
import com.example.learnandroidjava.utils.immerseStatusBar
import com.gyf.immersionbar.ImmersionBar

class App : ComponentActivity() {
    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        immerseStatusBar(window)
        immerseNavigationBar(window)

        setContent {
            ToolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // WebViewPage()
                    HomePage()
                }
            }
        }
    }
}

