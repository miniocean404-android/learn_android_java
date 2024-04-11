package com.example.learnandroidjava.learn.shared.adapter.recycler_view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amap.api.services.help.Tip


open class AMapSearchRecyclerAdapter(
    private val context: Context,
    private val recyclerView: RecyclerView,
    data: List<Tip>,
) :
    RecyclerView.Adapter<AMapSearchRecyclerAdapter.SearchHolder>(),
    View.OnClickListener {
    private var listener: OnItemClickListener? = null

    var data = data
        set(value) {
            field = value
            notifyDataSetChanged() // 刷新 Recycler 数据
        }

    class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val textView = TextView(context).apply {
            setOnClickListener(this@AMapSearchRecyclerAdapter)
        }

        // 设置 TextView 在 LinearLayout 布局中的参数
        textView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                gravity = Gravity.CENTER
                height = 300
            }

        return SearchHolder(textView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        (holder.itemView as TextView).text = data[position].name
    }

    /**
     * 自定义监听回调
     */
    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int, tip: Tip)
    }

    override fun onClick(view: View?) {
        if (listener != null) listener?.onItemClick(
            view,
            recyclerView.getChildAdapterPosition(view!!),
            data[recyclerView.getChildAdapterPosition(view)]
        )
    }

    open fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}


