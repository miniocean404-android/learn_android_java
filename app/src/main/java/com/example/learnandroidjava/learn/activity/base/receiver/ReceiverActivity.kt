package com.example.learnandroidjava.learn.activity.base.receiver

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.learn.activity.base.service.ServiceActivity
import com.example.learnandroidjava.databinding.ActivityReceiverBinding
import com.example.learnandroidjava.learn.shared.constant.DYNAMIC_BROADCAST_Receivers
import com.example.learnandroidjava.learn.shared.constant.STCTIC_BROADCAST_Receivers
import com.example.learnandroidjava.learn.shared.broadcast.receiver.DynamicReceiver
import com.example.learnandroidjava.learn.shared.broadcast.receiver.StaticReceiver

/**
 * 广播
 */
class ReceiverActivity : AppCompatActivity() {
    private val TAG: String? = ServiceActivity::class.simpleName

    private val bind: ActivityReceiverBinding by lazy {
        ActivityReceiverBinding.inflate(layoutInflater)
    }


    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        // 创建动态广播接受者
        val dynamicReceiver = DynamicReceiver()
        val filter = IntentFilter()
        filter.addAction(DYNAMIC_BROADCAST_Receivers)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(dynamicReceiver, filter, RECEIVER_EXPORTED)
        }else{
            registerReceiver(dynamicReceiver, filter)
        }


        // 发送广播给 静态 接受者(使用场景不多)
        bind.sendBroadcastStaticReceiverBtn.setOnClickListener {
            val intent = Intent(STCTIC_BROADCAST_Receivers)
            intent.setComponent(ComponentName(this@ReceiverActivity, StaticReceiver::class.java))
            sendBroadcast(intent)
        }

        // 发送广播给 动态 接受者
        bind.sendBroadcastDynamicReceiverBtn.setOnClickListener {
            val intent = Intent(DYNAMIC_BROADCAST_Receivers)
            sendBroadcast(intent)
        }
    }
}