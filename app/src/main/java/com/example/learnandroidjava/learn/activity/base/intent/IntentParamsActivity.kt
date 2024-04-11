package com.example.learnandroidjava.learn.activity.base.intent

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.databinding.ActivityIntentParamsBinding
import com.example.learnandroidjava.learn.shared.route_params.parcelable.ParcelableTest
import com.example.learnandroidjava.learn.shared.route_params.serializable.SerializableTest

class IntentParamsActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityIntentParamsBinding by lazy {
        ActivityIntentParamsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.base.setOnClickListener(this)
        binding.bundle.setOnClickListener(this)
        binding.serializable.setOnClickListener(this)
        binding.parcelable.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.base -> {
                val intent = Intent(this, IntentReceiveParamsActivity::class.java)
                intent.putExtra("name", "John")
                intent.putExtra("age", 25)
                startActivity(intent)
            }

            binding.bundle -> {
                val intent = Intent(this, IntentReceiveParamsActivity::class.java)

                val bundle = Bundle()
                bundle.putString("name1", "John")
                bundle.putInt("age1", 18)

                intent.putExtras(bundle)

                startActivity(intent)
            }

            // 必须实现 Serializable 才有传递的资格
            binding.serializable -> {
                SerializableTest().apply {
                    name = "John"
                    age = 18
                }.let {
                    val intent = Intent(this, IntentReceiveParamsActivity::class.java)
                    intent.putExtra("serializable_test", it)
                    startActivity(intent)
                }
            }

            // parcelable 性能更好，
            // android 必须使用 parcelable 不能用 serializable 因为它兼容安卓虚拟机
            binding.parcelable -> {
                ParcelableTest().apply {
                    name = "John"
                    age = 18
                }.let {
                    val intent = Intent(this, IntentReceiveParamsActivity::class.java)
                    intent.putExtra("parcelable_test", it)
                    startActivity(intent)
                }
            }
        }
    }
}