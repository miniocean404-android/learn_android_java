package com.example.learnandroidjava.learn.shared.utils.glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyGlideApp : AppGlideModule() {
    /**
     * Glide3初始化时会解析AndroidManifest.xml文件，但是Glide4并不需要加载AndroidManifest.xml文件，如果使用Glide4，这里返回 false 即可
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}