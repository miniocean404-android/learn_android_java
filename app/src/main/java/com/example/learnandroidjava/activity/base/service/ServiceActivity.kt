package com.example.learnandroidjava.activity.base.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.databinding.ActivityServiceBinding
import com.example.learnandroidjava.shared.service.CustomService

class ServiceActivity : AppCompatActivity() {
    private val TAG: String? = ServiceActivity::class.simpleName

    private val bind: ActivityServiceBinding by lazy {
        ActivityServiceBinding.inflate(layoutInflater)
    }

    private  val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i(TAG, "onServiceConnected: ")
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "onServiceDisconnected: ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        bind.startService.setOnClickListener {
            val intent = Intent(this, CustomService::class.java)
            startService(intent)
        }
        bind.stopService.setOnClickListener {
            val intent = Intent(this, CustomService::class.java)
            stopService(intent)
        }


        bind.startBind.setOnClickListener {
            val intent = Intent(this, CustomService::class.java)

            bindService(intent,connection,Context.BIND_AUTO_CREATE)
        }
        bind.stopBind.setOnClickListener {
            unbindService(connection)
        }
    }

    /**
     * 一般业务情况是这样写的，不用手动解绑
     */
    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}