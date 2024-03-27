package com.example.learnandroidjava

import android.app.Application
import android.view.Gravity
import android.widget.Toast
import com.facebook.drawee.backends.pipeline.Fresco
import com.hjq.toast.Toaster
import com.simple.spiderman.SpiderMan
import com.simple.spiderman.utils.R
import xcrash.XCrash

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        XCrash.init(this)
        SpiderMan.setTheme(R.style.SpiderManTheme_Dark)

        Fresco.initialize(this)
        Toaster.init(this)

        Toaster.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 300)
    }
}