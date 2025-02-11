package com.example.learnandroidjava.modal.entity

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val route: String, // 导航栏路由
    val title: String, // 导航栏标题
    val icon: ImageVector // 导航栏图片
)
