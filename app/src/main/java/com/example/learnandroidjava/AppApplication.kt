package com.example.learnandroidjava

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.Toaster
import com.simple.spiderman.SpiderMan
import com.simple.spiderman.utils.R
import xcrash.XCrash

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        XCrash.init(this)
        SpiderMan.setTheme(R.style.SpiderManTheme_Dark)

        Fresco.initialize(this)
        Toaster.init(this)
    }
}