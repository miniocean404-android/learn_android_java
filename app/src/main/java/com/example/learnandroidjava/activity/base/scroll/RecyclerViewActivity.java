package com.example.learnandroidjava.activity.base.scroll;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.learnandroidjava.R;
import com.example.learnandroidjava.shared.adapter.RecyclerViewAdapter;
import com.example.learnandroidjava.shared.bean.RecyclerViewBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private final String TAG = "mini_ocean";

    private final List<RecyclerViewBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        for (int i = 0; i < 10000; i++) {
            RecyclerViewBean bean = new RecyclerViewBean();
            bean.setName("Title " + i);
            data.add(bean);
        }


        RecyclerView r_view = findViewById(R.id.rv);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // r_view.setLayoutManager(layoutManager);

        // 网格布局
        // GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        // r_view.setLayoutManager(gridLayoutManager);

        // 瀑布流布局
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        r_view.setLayoutManager(staggeredGridLayoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(data, this);
        r_view.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnRecyclerViewItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(int position) {
                Log.e(TAG, "onRecyclerViewItemClick: " + position);
            }
        });
    }
}
