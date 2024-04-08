package com.example.learnandroidjava.modal.api

import com.example.learnandroidjava.modal.entity.resp.RespCategory
import com.example.learnandroidjava.shared.utils.Request
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("get_list/category")
    // 请求时候参数的名字
    suspend fun getCategoryApi(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int,
    ): RespCategory


    companion object {
        fun instance(): HomeApi {
            return Request.create(HomeApi::class.java)
        }
    }
}