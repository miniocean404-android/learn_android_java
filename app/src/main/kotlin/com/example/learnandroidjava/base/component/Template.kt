package com.example.learnandroidjava.base.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mini.ocean.tool.ui.theme.ToolTheme

@Composable
fun XX() {

}

@Preview(showBackground = true)
@Composable
fun TemplatePreview() {
    ToolTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
        }
    }
}