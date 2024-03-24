package com.example.learnandroidjava.activity.base.scroll

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.learnandroidjava.R
import com.example.learnandroidjava.R.id.view_pager
import com.example.learnandroidjava.adapter.ViewPageAdapter

class ViewPageActivity : AppCompatActivity() {
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_page)

        val pager = findViewById<ViewPager>(view_pager)

        val page1 = layoutInflater.inflate(R.layout.view_page_1, null)
        val page2 = layoutInflater.inflate(R.layout.view_page_2, null)
        val page3 = layoutInflater.inflate(R.layout.view_page_3, null)

        val list: MutableList<View> = ArrayList()
        list.add(page1)
        list.add(page2)
        list.add(page3)

        val adapter = ViewPageAdapter()
        adapter.list = list

        pager.adapter = adapter
    }
}