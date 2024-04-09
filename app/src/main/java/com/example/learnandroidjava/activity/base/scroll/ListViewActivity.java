package com.example.learnandroidjava.activity.base.scroll;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learnandroidjava.R;
import com.example.learnandroidjava.shared.adapter.ListViewAdapter;
import com.example.learnandroidjava.model.bean.ListViewBean;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private final String TAG = "mini_ocean";

    private final List<ListViewBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);


        for (int i = 0; i < 100; i++) {
            ListViewBean listViewBean = new ListViewBean();
            listViewBean.setName("Title " + i);
            data.add(listViewBean);
        }

        ListView list_view = findViewById(R.id.lv);
        list_view.setAdapter(new ListViewAdapter(data, this));

        list_view.setOnItemClickListener((parent, view, position, id) -> {
            Log.e(TAG, "setOnItemClickListener: " + position);
        });
    }
}
