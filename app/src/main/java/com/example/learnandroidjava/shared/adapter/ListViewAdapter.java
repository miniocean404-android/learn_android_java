package com.example.learnandroidjava.shared.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learnandroidjava.R;
import com.example.learnandroidjava.model.bean.ListViewBean;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private final List<ListViewBean> data;
    private final Context context;

    public ListViewAdapter(List<ListViewBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
       ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false);
            holder = new ViewHolder();
            holder.textView = view.findViewById(R.id.lv_item);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(data.get(i).getName());
        return view;
    }

    private static final class ViewHolder{
        TextView textView;
    }
}
