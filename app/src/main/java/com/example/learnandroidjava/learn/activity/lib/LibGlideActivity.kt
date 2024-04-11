package com.example.learnandroidjava.learn.activity.lib

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.learnandroidjava.databinding.ActivityLibGlideBinding

class LibGlideActivity : AppCompatActivity() {
    private val tag = LibGlideActivity::class.java.simpleName
    private val binding: ActivityLibGlideBinding by lazy {
        ActivityLibGlideBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // 如果使用 Glide.with(this).asBitmap() 加载那么需要使用 BitmapTransitionOptions.withCrossFade
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        val option = RequestOptions()
            // 请求图片时候的占位图
            .placeholder(android.R.drawable.ic_menu_gallery)
            // 请求图片失败时候的占位图（不设置展示 placeholder）
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            // 请求的 URL 或者 model 为空的时候展示的图片（不设置展示 placeholder）
            .fallback(android.R.drawable.ic_menu_camera)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            // 变换 CircleCrop() 圆形图片、RoundedCorners(30) 四个角统一指定、GranularRoundedCorners(1,2,3,4) 四个角分别指定、Rotate(30) 旋转
            .transform(CircleCrop())

        Glide.with(this)
            .load("https://picsum.photos/200/300")
            .apply(option)
            // 过渡加载图片
            .transition(withCrossFade(factory))
            // 重写图片尺寸
            .override(300,300)
            .into(binding.glideIv)
    }
}

