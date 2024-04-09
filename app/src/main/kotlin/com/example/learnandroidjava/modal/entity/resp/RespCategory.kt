package com.example.learnandroidjava.modal.entity.resp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RespCategory(
    val code: Int,
    val message: String,
    val data: List<Category>
)

data class Category(
    @Json(name = "title1")
    val title: String,
)