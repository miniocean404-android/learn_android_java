package com.example.learnandroidjava.project.page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learnandroidjava.project.modal.entity.NavigationItem


@Composable
fun HomePage() {
    BottomBar()
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

    // ProvideWindowInsets 与 navigationBarsPadding 用于处理状态栏和导航栏的 padding
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
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
        when (bottomIndex) {
            0 -> LearnPage()
            1 -> TaskPage()
            2 -> MinePage()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    BottomBar()
}