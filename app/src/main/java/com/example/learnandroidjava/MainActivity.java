package com.example.learnandroidjava;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.learnandroidjava.utils.DynamicNotice;


public class MainActivity extends AppCompatActivity {
    private final String Tag = "mini_ocean";
    private NotificationManager manager;

    // 翻译注解
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_input_content();
        show_hidden_process_bar();
        get_process_bar_process();
        send_notice();
    }

    private void change_text_view(String text) {
        TextView learn_text_view = findViewById(R.id.learn_text_view3);
        learn_text_view.setText(text);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void monitor_button_event() {
        Button learn_button_event = findViewById(R.id.learn_button_event);
       /*
            点击事件
         */
        learn_button_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Log.e(Tag, "长按事件触发");
                return true;
            }
        });

        /*
          触摸事件
          当返回值为true时，表示事件已经被处理，不会再传递给 onClick onLong 监听器
         */
        learn_button_event.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Log.e(Tag, "触摸事件触发" + event.getAction());
                return false;
            }
        });
    }

    private void get_input_content() {
        Button get_input_text = findViewById(R.id.get_input_text);
        get_input_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView learn_edit_text = findViewById(R.id.learn_edit_text);
                String text = learn_edit_text.getText().toString();
                Log.e(Tag, "获取输入框内容：" + text);
            }
        });
    }

    private void show_hidden_process_bar() {
        Button get_process_bar_btn = findViewById(R.id.get_process_bar_btn);
        get_process_bar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView learn_process_bar = findViewById(R.id.get_process_bar_btn);
                ProgressBar learn_progress_bar = findViewById(R.id.learn_progress_bar);
                if (learn_progress_bar.getVisibility() == View.VISIBLE) {
                    learn_progress_bar.setVisibility(View.GONE);
                } else {
                    learn_progress_bar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void get_process_bar_process() {
        Button get_process_bar_process_btn = findViewById(R.id.get_process_bar_process_btn);
        get_process_bar_process_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar learn_process_bar = findViewById(R.id.learn_progress_bar_process);
                int process = learn_process_bar.getProgress();
                process += 10;
                learn_process_bar.setProgress(process);
            }
        });
    }

    private void send_notice() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            DynamicNotice.requestNotificationPermission(this);
        }

        // 获取句柄
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // 安卓 8 以上需要创建通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("mini_ocean", "测试通知", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }


        // 创建跳转意图
        Intent intent = new Intent(this, NoticeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, "mini_ocean")
                .setContentTitle("通知标题")
                .setContentText("这是一条测试通知")
                // 通知图标必须是 alpha 绘制的图片
                .setSmallIcon(R.mipmap.ic_launcher)
                // 设置小图标颜色
                .setColor(Color.parseColor("#FF0000"))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.image_view_100_100))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();


        Button send_notice_btn = findViewById(R.id.send_notice_btn);
        Button hide_notice_btn = findViewById(R.id.hide_notice_btn);
        send_notice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.notify(1, notification);
                Log.e(Tag, "发送通知");
            }
        });

        hide_notice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.cancel(1);
                Log.e(Tag, "取消通知");
            }
        });
    }
}
