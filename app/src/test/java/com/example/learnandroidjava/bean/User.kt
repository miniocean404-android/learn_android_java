package com.example.learnandroidjava.bean

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(
    // 修改序列化 json 后的 key 名称
    @SerializedName("name1")
    @Expose
    var name: String,

    @Expose
    var age: Int,

    // 表示字段是否参与序列化和反序列化
    // 需要使用 GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() 创建 Gson 对象 注解才会生效
    // excludeFieldsWithoutExposeAnnotation 会忽略没有 @Expose 注解 和 serialize deserialize 为 false 的字段
    @Expose(serialize = false, deserialize = false)
    var email: String,

    @Expose
    var job: Job
)