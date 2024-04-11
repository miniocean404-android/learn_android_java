package com.example.learnandroidjava.learn.shared.adapter.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroidjava.R

class ViewPage2Adapter : RecyclerView.Adapter<ViewPage2Adapter.ViewPager2Holder>() {
    private val titles: MutableList<String> = ArrayList()
    private val colors: MutableList<Int> = ArrayList()

    init {
        repeat(5)  {
            titles.add(randomString())
        }

        colors.add(R.color.white)
        colors.add(R.color.purple_700)
        colors.add(R.color.red)
        colors.add(R.color.purple_200)
        colors.add(R.color.black)
        colors.add(R.color.purple_500)
    }

    private fun randomString(): String {
        val chars = ('A'..'Z') + ('a'..'z')
        return (1..10)
            .map { chars.random() }
            .joinToString("")
    }

    override fun getItemCount(): Int {
        return titles.size
    }


    /**
     * 视图支架创建时
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPager2Holder {
        return ViewPager2Holder(LayoutInflater.from(parent.context).inflate(R.layout.view_pager2_item, parent, false))
    }

    /**
     * 视图支架滑动切换绑定时
     */
    override fun onBindViewHolder(holder: ViewPager2Holder, position: Int) {
        holder.tv.text = titles[position]
        holder.container.setBackgroundResource(colors[position])
    }

    inner class ViewPager2Holder(layout: View) : RecyclerView.ViewHolder(layout) {
        val tv : TextView = itemView.findViewById(R.id.tv_title)
        val container : RelativeLayout  = itemView.findViewById(R.id.view_pager2_item_box)
    }
}
