package com.example.learnandroidjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnandroidjava.R;
import com.example.learnandroidjava.bean.ListViewBean;
import com.example.learnandroidjava.bean.RecyclerViewBean;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<RecyclerViewBean> data;
    private final Context context;

    public RecyclerViewAdapter(List<RecyclerViewBean> data, Context context) {
        this.data = data;
        this.context = context;
    }


    /**
     * 创建 ViewHolder
     */
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * 绑定数据
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.rv_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onRecyclerViewItemClick(getBindingAdapterPosition());
                    }
                }
            });
        }
    }

    private static OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        onRecyclerViewItemClickListener = listener;
    }

    public  interface OnRecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }
}
