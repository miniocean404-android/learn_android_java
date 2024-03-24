package com.example.learnandroidjava.shared.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.hjq.toast.Toaster

class StaticReceiver : BroadcastReceiver(){
    private val TAG: String? = StaticReceiver::class.simpleName
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toaster.show("静态广播接受者")
    }
}