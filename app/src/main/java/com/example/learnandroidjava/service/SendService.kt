package com.example.learnandroidjava.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * 用于后台播放音乐等后台服务
 */
class SendService : Service() {

    private val TAG = SendService::class.simpleName

    override fun onCreate() {
        Log.i(TAG, "onCreate: ")
        super.onCreate()
    }

    /**
     * 代替了 onStart() 方法
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * onBind 与 onUnbind 与 activity 绑定，activity 存在时候服务启动 activity 销毁时，服务也会销毁
     */
    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "onBind: ")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "onUnbind: ")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy: ")
        super.onDestroy()
    }
}