package com.example.learnandroidjava.model.bean

// JvmOverloads constructor 设置为有参无参构造
open class RxJavaResponse @JvmOverloads constructor(
    // JvmField 包含 set get 方法
    @JvmField var code: Int = 0,
    @JvmField var data: RxJavaSuccessResponse? = null,
    @JvmField var message: String = ""
)