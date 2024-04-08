package com.example.learnandroidjava.modal.entity.resp

data class RespCategory(
    val code: Int,
    val message: String,
    val data: List<Category>
)


data class Category(
    val title: String
)