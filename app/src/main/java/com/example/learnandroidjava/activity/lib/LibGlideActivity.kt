package com.example.learnandroidjava.activity.lib

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.learnandroidjava.databinding.ActivityLibGlideBinding
import com.example.learnandroidjava.utils.glide.MyGlideApp

class LibGlideActivity : AppCompatActivity() {
    private val tag = LibGlideActivity::class.java.simpleName
    private val binding: ActivityLibGlideBinding by lazy {
        ActivityLibGlideBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

         Glide.with(this).load("https://picsum.photos/200/300").into(binding.glideIv)
//        GlideApp.with(this).load("https://goo.gl/gEgYUd").into(binding.glideIv)

//        Glide.with(this).load("https://goo.gl/gEgYUd")
//        Glide.with(this).load("").defaultImage().into(binding.glideIv)
    }
}