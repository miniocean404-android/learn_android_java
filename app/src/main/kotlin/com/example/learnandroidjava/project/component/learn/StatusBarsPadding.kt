package com.example.learnandroidjava.project.component.learn

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun StatusBarsPadding(){
    Row (modifier = Modifier.statusBarsPadding()){
        Text(text = "状态栏 padding ")
    }
}