package com.example.learnandroidjava.component.common.custom_app_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable RowScope.() -> Unit
) {
    val statusBarHeightDp = LocalDensity.current.run {
        WindowInsets.statusBars.getTop(this).toDp()
    }

    val APP_BAR_HEIGHT = 56.dp

    Row(
        modifier = Modifier
            .background(
                Brush.linearGradient(
                    listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
                )
            )
            // 解决小窗模式下状态栏消失的问题
            .windowInsetsPadding(TopAppBarDefaults.windowInsets)
            .fillMaxWidth()
            .height(APP_BAR_HEIGHT)
            // 合并两个 modifier
            .then(modifier),
        // 内容对其方式
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        content()
    }
}

@Preview
@Composable
fun TopBarPreview() {
//    CustomTopAppBar {
//        Text(text = "App bar")
//    }
}

