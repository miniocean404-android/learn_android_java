package com.example.learnandroidjava.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnandroidjava.modal.entity.ArticleData
import com.example.learnandroidjava.modal.store.UserInfoStore
import kotlinx.coroutines.launch

class ArticleVM(context: Context) : ViewModel() {
    // datastore
    private val userInfoStore = UserInfoStore(context)

    init {
        // 获取 datastore 数据
        viewModelScope.launch {
            val username = userInfoStore.username
        }
    }

    // 新闻列表数据
    var newsList = mutableListOf(
        ArticleData(
            "外包胜利了--开头",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
        ArticleData(
            "外包胜利了---末尾",
            "程序员再也不能对外包嗤之以鼻了，外包也不再小心翼翼了#程序员 #外包程序员 #工作 #上海找工作上次找工作的时候从不看外包",
            "2021-01-01",
            "https://tva3.sinaimg.cn/large/ceeb653ely8h3brycd2laj20j50i0ab6.jpg"
        ),
    )
}