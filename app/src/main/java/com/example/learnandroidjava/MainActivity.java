package com.example.learnandroidjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final String Tag = "MainActivity";

    // 翻译注解
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView learn_text_view = findViewById(R.id.learn_text_view);
//        learn_text_view.setText("Hello World");

        Button learn_button_event = findViewById(R.id.learn_button_event);

        /*
            点击事件
         */
        learn_button_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learn_text_view.setText("点击事件触发");
                Log.e(Tag, "点击事件触发");
            }
        });

        /*
            长按事件
            当返回值为true时，表示事件已经被处理，不会再传递给 onClick 监听器
         */
        learn_button_event.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                learn_text_view.setText("长按事件触发");
                Log.e(Tag, "长按事件触发");
                return true;
            }
        });

        /*
          触摸事件
          当返回值为true时，表示事件已经被处理，不会再传递给 onClick onLong 监听器
         */
        learn_button_event.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                learn_text_view.setText("触摸事件触发");
                Log.e(Tag, "触摸事件触发" + event.getAction());
                return false;
            }
        });
    }


}
