package com.example.learnandroidjava.activity.base.event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.databinding.ActivityViewBindingActivityBinding

class ViewBindingActivity : AppCompatActivity() {

    private val binding:ActivityViewBindingActivityBinding by lazy {
        ActivityViewBindingActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewBindBtn.setOnClickListener{
            binding.viewBindBtn.text = "视图绑定修改文字"
        }
    }
}