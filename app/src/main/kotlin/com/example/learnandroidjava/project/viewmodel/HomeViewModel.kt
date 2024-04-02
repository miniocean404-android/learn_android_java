package com.example.learnandroidjava.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.learnandroidjava.project.modal.entity.Category

class HomeViewModel : ViewModel() {
    // 选中的分类下标
    var index by mutableIntStateOf(0)

    // 分类标题
    val categories = mutableListOf(
        Category("思想政治"),
        Category("数学"),
        Category("英语"),
        Category("物理"),
    )
}