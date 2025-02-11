package com.example.learnandroidjava.vm

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnandroidjava.component.logic.notification.intl.NotificationViewModel
import com.example.learnandroidjava.component.common.swiper.intl.SwiperViewModel
import com.example.learnandroidjava.modal.entity.resp.Category
import com.example.learnandroidjava.component.common.swiper.entity.DataSwiper
import com.example.learnandroidjava.modal.api.HomeApi
import com.example.learnandroidjava.modal.entity.DataType
import com.example.learnandroidjava.modal.store.UserInfoStore
import kotlinx.coroutines.launch

class HomeVM(context: Context) : ViewModel(), SwiperViewModel, NotificationViewModel {
    // 请求
    private val homeApiIns = HomeApi.instance()
    // datastore
    private val userInfoStore = UserInfoStore(context)


    suspend fun getCategories() {
        val res = homeApiIns.getCategoryApi(1, 10)
        if (res.code == 0) {
            categories = res.data

            // 存储 store
            viewModelScope.launch {
                userInfoStore.save("name")
            }
        } else {
            val message = res.message
        }
    }


    // 分类标题
    var categories by mutableStateOf(
        listOf(
            Category("思想政治"),
            Category("数学"),
            Category("英语"),
            Category("物理"),
        )
    )
        private set

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
        DataSwiper("https://tva3.sinaimg.cn/large/ceeb653ely8h3bzbnzbfdj20j60j7my0.jpg"),
        DataSwiper("https://tva3.sinaimg.cn/large/ceeb653ely8h3c123gicqj20hs0hs3zc.jpg"),
        DataSwiper("https://tva3.sinaimg.cn/large/ceeb653ely8h3c2sixi3tj20m70mqgm9.jpg"),
    )

    // 通知数据
    override val notifications = listOf(
        "通知滚动1通知滚动1通知滚动1通知滚动1通知滚动1通知滚动1通知滚动1通知滚动1",
        "通知滚动2",
        "通知滚动3",
        "通知滚动4",
        "通知滚动5"
    )
}