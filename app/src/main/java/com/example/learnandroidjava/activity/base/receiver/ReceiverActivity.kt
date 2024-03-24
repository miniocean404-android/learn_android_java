package com.example.learnandroidjava.activity.base.receiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.R
import com.example.learnandroidjava.activity.base.service.ServiceActivity
import com.example.learnandroidjava.databinding.ActivityReceiverBinding
import com.example.learnandroidjava.shared.constant.DYNAMIC_BROADCAST_Receivers
import com.example.learnandroidjava.shared.constant.STCTIC_BROADCAST_Receivers
import com.example.learnandroidjava.shared.receiver.CustomReceiver

/**
 * 广播
 */
class ReceiverActivity : AppCompatActivity() {
    private val TAG: String? = ServiceActivity::class.simpleName

    private val bind: ActivityReceiverBinding by lazy {
        ActivityReceiverBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        // 创建动态广播接受者
        val customReceiver = CustomReceiver()
        val filter = IntentFilter()
        filter.addAction(DYNAMIC_BROADCAST_Receivers)
        registerReceiver(customReceiver, filter)

        // 发送广播给静态接受者(使用场景不多)
        bind.sendBroadcastStaticReceiverBtn.setOnClickListener {
            val intent = Intent(STCTIC_BROADCAST_Receivers)
            sendBroadcast(intent)
        }

        // 发送广播给动态接受者
        bind.sendBroadcastDynamicReceiverBtn.setOnClickListener {
            val intent = Intent(DYNAMIC_BROADCAST_Receivers)
            sendBroadcast(intent)
        }
    }
}