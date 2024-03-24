package com.example.learnandroidjava.shared.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class StaticReceiver : BroadcastReceiver(){
    private val TAG: String? = StaticReceiver::class.simpleName
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i(TAG, "BroadcastReceiver onReceive: 静态广播接受者")
    }
}