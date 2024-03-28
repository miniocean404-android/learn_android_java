package com.example.learnandroidjava.activity.base.intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.databinding.ActivityIntentReceiveParamsBinding
import com.example.learnandroidjava.shared.parcelable.ParcelableTest
import com.example.learnandroidjava.shared.serializable.SerializableTest
import com.hjq.toast.Toaster

class IntentReceiveParamsActivity : AppCompatActivity() {
    private val binding: ActivityIntentReceiveParamsBinding by lazy {
        ActivityIntentReceiveParamsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 基础类型传参
        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")

        Toaster.show("Name: $name, Age: $age")

        // Bundle传参
        val name1 = intent.getStringExtra("name1")
        val age1 = intent.getIntExtra("age1", 0)
        Toaster.show("Name1: $name1, Age1: $age1")


        // serializable 传参
        val test = intent.getSerializableExtra("serializable_test") as SerializableTest
        Toaster.show("Name1: ${test.name}, Age1: ${test.age}")

        // parcelable 传参
        val parcelableTest = intent.getParcelableExtra("parcelable_test") as? ParcelableTest
        Toaster.show("Name1: ${parcelableTest?.name}, Age1: ${parcelableTest?.age}")
    }
}