package com.example.learnandroidjava.shared.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class ViewPageAdapter : PagerAdapter() {
    var list: List<View>? = null
    override fun getCount(): Int {
        return list?.size ?: 0
    }

    /**
     * 将指定的 view 添加到 viewpager 中 返回一个 key 值
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = list?.get(position)
        container.addView(view)
        return view!!
    }

    /**
     * instantiateItem 返回的 key 与 页面视图的展示的是否是同一个 key
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = list?.get(position)
        container.removeView(view)
    }
}