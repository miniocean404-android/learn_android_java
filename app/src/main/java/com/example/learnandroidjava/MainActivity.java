package com.example.learnandroidjava;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnandroidjava.activity.BaseActivity;
import com.example.learnandroidjava.activity.ConstraintLayoutActivity;
import com.example.learnandroidjava.activity.FrameByFrameActivity;
import com.example.learnandroidjava.activity.FrameLayoutActivity;
import com.example.learnandroidjava.activity.GridLayoutActivity;
import com.example.learnandroidjava.activity.ListViewActivity;
import com.example.learnandroidjava.activity.OkHttpActivity;
import com.example.learnandroidjava.activity.PropAnimActivity;
import com.example.learnandroidjava.activity.RecyclerViewActivity;
import com.example.learnandroidjava.activity.RelativeLayoutActivity;
import com.example.learnandroidjava.activity.TableLayoutActivity;
import com.example.learnandroidjava.activity.TweenActivity;
import com.example.learnandroidjava.activity.UseFragmentActivity;
import com.example.learnandroidjava.activity.ViewPageActivity;


public class MainActivity extends AppCompatActivity {
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jump(this,R.id.go_base_btn, BaseActivity.class);
        jump(this,R.id.go_relative_layout_btn, RelativeLayoutActivity.class);
        jump(this,R.id.go_frame_layout_btn, FrameLayoutActivity.class);
        jump(this,R.id.go_table_layout_btn, TableLayoutActivity.class);
        jump(this,R.id.go_grid_layout_btn, GridLayoutActivity.class);
        jump(this,R.id.go_constraint_layout_btn, ConstraintLayoutActivity.class);
        jump(this,R.id.go_list_view_btn, ListViewActivity.class);
        jump(this,R.id.go_recycler_view_btn, RecyclerViewActivity.class);
        jump(this,R.id.go_frame_by_frame_btn, FrameByFrameActivity.class);
        jump(this,R.id.go_tween_btn, TweenActivity.class);
        jump(this,R.id.go_prop_btn, PropAnimActivity.class);
        jump(this,R.id.go_view_page_btn, ViewPageActivity.class);
        jump(this,R.id.go_ok_http_btn, OkHttpActivity.class);
        jump(this,R.id.go_use_fragment_btn, UseFragmentActivity.class);
    }

    private void jump(Activity activity,@IdRes int id,Class<?> cls){
        Intent intent = new Intent(activity, cls);
        Button btn = findViewById(id);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
