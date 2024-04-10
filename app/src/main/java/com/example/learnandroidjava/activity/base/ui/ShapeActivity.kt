package com.example.learnandroidjava.activity.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.learnandroidjava.databinding.ActivityShapeBinding

class ShapeActivity : AppCompatActivity() {
    private val binding: ActivityShapeBinding by lazy {
        ActivityShapeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}