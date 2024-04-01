package com.example.learnandroidjava.base.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnandroidjava.theme.ToolTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldLayout(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ScaffoldLayout") },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                })
        },
    ) { innerPadding ->
        ScaffoldLayoutBody(modifier.padding(innerPadding))
    }
}

// modifier.padding(8.dp) 表示在传参的 padding 的基础上基础添加 padding
@Composable
fun ScaffoldLayoutBody(modifier: Modifier = Modifier) {
    Column(modifier.padding(8.dp)) {
        Text(text = "文本1")
        Text(text = "文本2")
    }
}

@Preview(showBackground = true)
@Composable
fun ScaffoldLayoutPreview() {
    ToolTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ScaffoldLayout()
        }
    }
}