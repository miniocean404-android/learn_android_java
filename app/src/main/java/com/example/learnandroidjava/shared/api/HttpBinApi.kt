package com.example.learnandroidjava.shared.api

import com.example.learnandroidjava.shared.bean.RxJavaResponse
import io.reactivex.rxjava3.core.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 *
 * 方法注解： @GET @POST @PUT @DELETE @HEAD @PATCH @HTTP(method = "GET",path = "get",hasBody = true)
 * hasBody 是否有请求体
 *
 * 标记注解： @FormUrlEncoded @Multipart @Streaming
 *
 *
 * 参数注解： @Query @QueryMap @Field @FieldMap @Part @PartMap @Body
 * @QueryMap 以 Map 的形式传递参数
 * @Field @FieldMap 与  @Query @QueryMap 类似，但是用于 POST 请求
 * @Part @PartMap 结合 Multipart 使用
 *
 * 其他注解： @Path @Url @Header @Headers @Url
 *
 *
 */
interface HttpBinApi {
    @GET("user/login")
    // 请求时候参数的名字
    fun get(
        @Query("name") name: String,
        @Query("password") pwd: String,
    ): Call<ResponseBody>

    /**
     * @Path 用于 /xxx/{pageNum}?xxx=xxx 中的 {pageNum} 占位符替换为 path 参数
     * @Header 添加请求头
     * @Url 请求完整地址包含 https://
     */
    @Headers(
        "os: android",
        "version: 1.0"
    )
    @POST("xxx/{pageNum}")
    @FormUrlEncoded // form 表单提交注解
    fun post(
        @Path("pageNum") path: String,
        @Header("os") os: String,
        @Field("name") name: String,
        @Field("password") pwd: String,
        @Url url: String
    ): Call<ResponseBody>


    /**
     * 自己创建 okhttp 的 RequestBody 与 postSync 创建 body 方式一致
     * Flowable 是 rxjava3 的观察者模式
     */
    @POST("post/auth")
    fun requestBody(@Body body: RequestBody): Flowable<RxJavaResponse>


    @POST("xxx/upload")
    @Multipart
    fun upload(@Part file: MultipartBody.Part): Call<ResponseBody>

    @POST("xxx/upload")
    @Multipart
    fun uploads(@PartMap file: MultipartBody.Part): Call<ResponseBody>


    /**
     * 下载文件过大时候，使用 @Streaming 注解，有效避免内存溢出
     */
    @Streaming
    @GET("xxx/download")
    fun download(): Call<ResponseBody>
}