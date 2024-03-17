package com.example.learnandroidjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class NoticeActivity: AppCompatActivity() {
    private val tag = "mini_ocean"

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(tag, "进入 NoticeActivity onCreate")
    }
}