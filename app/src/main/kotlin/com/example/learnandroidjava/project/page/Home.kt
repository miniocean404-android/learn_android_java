package com.example.learnandroidjava.project.page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learnandroidjava.project.modal.entity.NavigationItem
import mini.ocean.tool.ui.theme.ToolTheme
import mini.ocean.tool.ui.theme.White100

class HomePage : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomBar()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBar() {
    val bottomItems = listOf(
        NavigationItem("学习", Icons.Filled.Home),
        NavigationItem("任务", Icons.Filled.DateRange),
        NavigationItem("我的", Icons.Filled.Person),
    )

    var bottomIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(bottomBar = {
        NavigationBar(containerColor = White100) {
            bottomItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == bottomIndex,
                    onClick = {
                        bottomIndex = index
                    },
                    label = {
                        Text(text = item.title)
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null
                        )
                    },
                )
            }
        }
    }) {
        Text(text = "current $bottomIndex")
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    BottomBar()
}