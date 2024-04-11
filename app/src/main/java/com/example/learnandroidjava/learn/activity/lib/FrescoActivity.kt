package com.example.learnandroidjava.learn.activity.lib

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.databinding.ActivityFrescoBinding
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.generic.RoundingParams


/**
 * 文章：https://www.fresco-cn.org/docs/requesting-multiple-images.html
 * Fresco 不支持相对路径
 */
class FrescoActivity : AppCompatActivity() {

    private val binding: ActivityFrescoBinding by lazy {
        ActivityFrescoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        // 圆角参数
        val roundingParams = RoundingParams.fromCornersRadius(30f)
        val builder = GenericDraweeHierarchyBuilder(getResources())
        // 自定义 DraweeHierarchy
        val hierarchy = builder.setRoundingParams(roundingParams).build()


        val uri =
            Uri.parse("https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d3093e58d6e4434ea30c3f241d5d275f~tplv-k3u1fbpfcp-watermark.image")

//        val controller: DraweeController = Fresco.newDraweeControllerBuilder()
//            .setUri(uri)
//            // 点击重试
//            .setTapToRetryEnabled(true)
//            .setOldController(binding.frescoView.controller)
////            .setControllerListener(listener)
//            .build()

//        binding.frescoView.controller = controller


        // 给控件设置DraweeHierarchy
        binding.frescoView.setHierarchy(hierarchy);
        // 使用Fresco加载图片
        binding.frescoView.setImageURI(uri)
    }
}