package com.example.learnandroidjava.learn.activity.base.scroll

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.learnandroidjava.R
import com.example.learnandroidjava.R.id.view_page2
import com.example.learnandroidjava.learn.shared.adapter.recycler_view.ViewPage2Adapter

class ViewPage2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_page2_activity)

        val pager2 = findViewById<ViewPager2>(view_page2)
        pager2.adapter = ViewPage2Adapter()
    }
}