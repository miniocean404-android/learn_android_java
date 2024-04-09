package com.example.learnandroidjava.activity.lib

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.R
import com.example.learnandroidjava.model.api.HttpBinApi
import com.example.learnandroidjava.model.bean.RxJavaResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.Callback
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.reactivestreams.Publisher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import kotlin.concurrent.thread

class OkHttpActivity : AppCompatActivity() {
    private val tag = "mini_ocean"
    private val url = "https://httpbin.org/"
    val cookieCache: MutableMap<String, MutableList<okhttp3.Cookie>> = mutableMapOf()

    private val client by lazy {

        val client = OkHttpClient.Builder()


        // 开启缓存，符合规则则从本地读取缓存
        client.cache(Cache(cacheDir, 10 * 1024 * 1024))

        client.cookieJar(object : CookieJar {
            override fun loadForRequest(url: HttpUrl): MutableList<okhttp3.Cookie> {
                // 获取指定网址的 Cookie 列表
                return cookieCache[url.toString()] ?: mutableListOf()
            }

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                // 将指定网址的 Cookie 存入缓存
                cookieCache[url.toString()] = cookies.toMutableList()
            }
        })

        // 拦截器可以添加多个
        // 请求拦截器
        client.addInterceptor { chain ->
            val request = chain.request()

            // 复制一个一样的 Request，然后添加 请求头
            request.newBuilder().addHeader("User-Agent", "OkHttp Example")
                .addHeader("os", "android").build()

            val response = chain.proceed(request)
            response
        }

        // 响应拦截器
        client.addNetworkInterceptor { chain ->
            val request = chain.request()

            request.header("User-Agent")

            val response = chain.proceed(request)
            response
        }

        client.build()
    }

    private val clientRetrofit by lazy {
        val client = Retrofit.Builder()
        client.baseUrl(url)
        client.addConverterFactory(GsonConverterFactory.create()) // 添加 Gson Bean 转换器

        // 添加 cookieJar
        client.callFactory(OkHttpClient.Builder().cookieJar(object : CookieJar {
            override fun loadForRequest(url: okhttp3.HttpUrl): MutableList<okhttp3.Cookie> {
                return cookieCache[url.toString()] ?: mutableListOf()
            }

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                cookieCache[url.toString()] = cookies.toMutableList()

            }
        }).build())


        client.build()
    }

    private val httpBinApi by lazy {
        clientRetrofit.create(HttpBinApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ok_http)

        getSync()
        getASync()
        postSync()
    }

    private fun getSync() {
        val t = thread {
            val request = Request.Builder().url("$url/get?a=1&b=2").build()
            val response = client.newCall(request).execute()
            val bodyStr = response.body?.string()

            Log.i(tag, "OkHttpActivity getSync: $bodyStr 111111111111111111")
        }

        // 等待新线程结束
        t.join()
    }

    private fun getASync() {
        val request = Request.Builder().url("$url/get?a=1&b=2").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                Log.i(tag, "OkHttpActivity getASync onFailure: ${e.message}")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    val bodyStr = response.body?.string()

                    Log.i(tag, "OkHttpActivity getASync onResponse: $bodyStr")
                }
            }
        })
    }

    private fun postSync() {
        val t = thread {
            val formBody = FormBody.Builder().add("a", "1").add("b", "2").build()
            val request = Request.Builder().url("$url/post?a=1&b=2").post(formBody).build()
            val response = client.newCall(request).execute()
            val body = response.body?.string()

            Log.i(tag, "OkHttpActivity postSync: $body ")
        }

        // 等待新线程结束
        t.join()
    }

    fun retrofitPost() {
        val call = httpBinApi.post("1", "android", "name", "pwd", "https://httpbin.org/post")
        call.enqueue(object : retrofit2.Callback<okhttp3.ResponseBody> {
            override fun onResponse(
                call: retrofit2.Call<okhttp3.ResponseBody>,
                response: retrofit2.Response<okhttp3.ResponseBody>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()?.string()
                    Log.i(tag, "OkHttpActivity retrofitPost onResponse: $body")
                }
            }

            override fun onFailure(call: retrofit2.Call<okhttp3.ResponseBody>, t: Throwable) {
                Log.i(tag, "OkHttpActivity retrofitPost onFailure: ${t.message}")
            }
        })
    }

    @SuppressLint("CheckResult")
    fun retrofitNestRequest() {
        val from = FormBody.Builder().add("a", "1").build()
        val observer = httpBinApi.requestBody(from)

        observer
            .flatMap(Function<RxJavaResponse, Publisher<RxJavaResponse>> { response ->
                return@Function httpBinApi.requestBody(from)
            })
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<RxJavaResponse> { resp ->
                Log.i(tag, "OkHttpActivity retrofitNestRequest: ${resp.code}")
            })

    }

    fun retrofitUpload() {
        val file = File("")
        val part = MultipartBody.Part.createFormData(
            "file",
            file.name,
            file.asRequestBody("text/plain".toMediaTypeOrNull())
        )

        val call = httpBinApi.upload(part)
        Log.i("TAG", "retrofitUpload: ${call.execute().body()?.string()}")
    }

    fun retrofitDownload() {
        val inputStream = httpBinApi.download().execute().body()?.byteStream()
        val outputStream = FileOutputStream("C://xxx")

        var len: Int
        val buffer = ByteArray(4096)
        while (inputStream?.read(buffer).also { len = it!! } != -1) {
            outputStream.write(buffer, 0, len)
        }

        outputStream.close()
        inputStream?.close()
    }
}