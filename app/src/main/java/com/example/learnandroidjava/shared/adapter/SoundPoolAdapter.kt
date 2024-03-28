package com.example.learnandroidjava.shared.adapter

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroidjava.shared.bean.Sound


open class SoundPoolAdapter(
    private val context: Context,
    private val recyclerView: RecyclerView,
    private val data: List<Sound>
) :
    RecyclerView.Adapter<SoundPoolAdapter.SoundPoolHolder>(), View.OnClickListener {
    private var listener: OnItemClickListener? = null

    class SoundPoolHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundPoolHolder {
        val textView = TextView(context)

        // 设置 TextView 在 LinearLayout 布局中的参数
        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER
        layoutParams.height = 300

        textView.layoutParams = layoutParams

        textView.setOnClickListener(this)

        return SoundPoolHolder(textView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SoundPoolHolder, position: Int) {
        (holder.itemView as TextView).text = data[position].name
    }

    override fun onClick(view: View?) {
        if (listener != null) listener?.onItemClick(recyclerView.getChildAdapterPosition(view!!))
    }

    open fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

