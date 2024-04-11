package com.example.learnandroidjava.learn.activity.base.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.R

class TableLayoutActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_table)
    }
}