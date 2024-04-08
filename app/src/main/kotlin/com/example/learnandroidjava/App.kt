package com.example.learnandroidjava

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.example.learnandroidjava.page.AppNavHost
import com.example.learnandroidjava.shared.local.LocalHomeVm
import com.example.learnandroidjava.shared.theme.ToolTheme
import com.example.learnandroidjava.shared.utils.immerseNavigationBar
import com.example.learnandroidjava.shared.utils.immerseStatusBar
import com.example.learnandroidjava.vm.HomeVM

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

                    // 提供全局的 HomeVM
                    CompositionLocalProvider(value = LocalHomeVm provides HomeVM()) {
                        AppNavHost()
                    }
                }
            }
        }
    }
}

