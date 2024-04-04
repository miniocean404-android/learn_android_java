package com.example.learnandroidjava.project.vm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.learnandroidjava.project.component.swiper.intl.SwiperViewModel
import com.example.learnandroidjava.project.modal.entity.Category
import com.example.learnandroidjava.project.component.swiper.entity.DataSwiper
import com.example.learnandroidjava.project.modal.entity.DataType

class HomeViewModel : ViewModel(), SwiperViewModel {
    // 分类标题
    val categories = mutableListOf(
        Category("思想政治"),
        Category("数学"),
        Category("英语"),
        Category("物理"),
    )
    var categoryIndex by mutableIntStateOf(0)


    // 类型数据
    val types by mutableStateOf(
        listOf(
            DataType("相关咨询", Icons.Default.Description),
            DataType("视频课程", Icons.Default.SmartDisplay)
        )
    )
    var typeIndex by mutableIntStateOf(0)

    // 轮播图
    override val swipes = listOf(
        DataSwiper("https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"),
        DataSwiper("https://tva3.sinaimg.cn/large/ceeb653ely8h3box301ltj20hs0h9dgm.jpg"),
        DataSwiper("https://tva3.sinaimg.cn/large/006APoFYly8h3bv4stxwbj30ew0er3yx.jpg"),
    )
}