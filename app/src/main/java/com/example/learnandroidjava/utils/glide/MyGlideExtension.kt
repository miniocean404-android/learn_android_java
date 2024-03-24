package com.example.learnandroidjava.utils.glide

import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions

@GlideExtension
class MyGlideExtension() {
    @GlideOption
    public fun defaultImage(options: BaseRequestOptions<*>): BaseRequestOptions<*> {
        return options
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            .fallback(android.R.drawable.ic_menu_camera)
    }


}

@GlideOption
fun RequestOptions.miniThumb(size: Int): RequestOptions {
    return this.fitCenter()
        .override(100)
}