package com.example.learnandroidjava.shared.broadcast.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.hjq.toast.Toaster

class DynamicReceiver: BroadcastReceiver() {
    private val TAG: String? = DynamicReceiver::class.simpleName
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toaster.show("动态广播接受者")
    }
}