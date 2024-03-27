package com.example.learnandroidjava.activity.base.api

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amap.api.services.auto.ListData.Content
import com.example.learnandroidjava.R

/**
 * 会存储到手机包中的 shared_prefs 中，是 xml 文件
 * 只存储少量数据
 *
 * 默认 MODE_PRIVATE 会覆盖 ，MODE_APPEND 会追加
 */
class SharePreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_preferences)
    }

    fun saveSp(){
        val sp = getSharedPreferences("mini_ocean", Context.MODE_PRIVATE)
        sp.edit().putString("key","value").apply()
    }

    fun getSp(){
        val sp = getSharedPreferences("mini_ocean", Context.MODE_PRIVATE)
        sp.getString("key","默认值")
    }
}