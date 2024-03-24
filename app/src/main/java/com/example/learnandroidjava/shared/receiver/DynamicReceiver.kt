package com.example.learnandroidjava.shared.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DynamicReceiver: BroadcastReceiver() {
    private val TAG: String? = DynamicReceiver::class.simpleName
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i(TAG, "BroadcastReceiver onReceive: 动态广播接受者")
    }
}