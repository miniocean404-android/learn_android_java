package com.example.learnandroidjava.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.R
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class OkHttpActivity : AppCompatActivity() {
    private val tag = "mini_ocean"
    private val url = "https://httpbin.org/"
    private var client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ok_http)

        getSync()
        getASync()
        postSync()
    }

    private fun getSync(){
        val t = thread {
            val request = Request.Builder().url("$url/get?a=1&b=2").build()
            val response = client.newCall(request).execute()
            val bodyStr = response.body?.string()

            Log.i(tag, "OkHttpActivity getSync: $bodyStr 111111111111111111")
        }

        // 等待新线程结束
        t.join()
    }
    private fun getASync(){
        val request = Request.Builder().url("$url/get?a=1&b=2").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                Log.i(tag, "OkHttpActivity getASync onFailure: ${e.message}")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful){
                    val bodyStr = response.body?.string()

                    Log.i(tag, "OkHttpActivity getASync onResponse: $bodyStr")
                }
            }
        })
    }
    private fun postSync(){
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
}