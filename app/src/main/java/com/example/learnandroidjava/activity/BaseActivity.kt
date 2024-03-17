package com.example.learnandroidjava.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import com.example.learnandroidjava.R
import com.example.learnandroidjava.utils.DynamicNotice.requestNotificationPermission

class BaseActivity: AppCompatActivity() {
    private val tag = "mini_ocean"
    private var manager: NotificationManager? = null


    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base)

        get_input_content()
        show_hidden_process_bar()
        get_process_bar_process()
        send_notice()
        toolbar_click()
        show_dialog()
        show_pop_window()
    }

    private fun change_text_view(text: String) {
        val learn_text_view = findViewById<TextView>(R.id.learn_text_view3)
        learn_text_view.text = text
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun monitor_button_event() {
        val learn_button_event = findViewById<Button>(R.id.learn_button_event)
        /*
            点击事件
         */learn_button_event.setOnClickListener { Log.e(tag, "点击事件触发") }

        /*
            长按事件
            当返回值为true时，表示事件已经被处理，不会再传递给 onClick 监听器
         */learn_button_event.setOnLongClickListener {
            Log.e(tag, "长按事件触发")
            true
        }

        /*
          触摸事件
          当返回值为true时，表示事件已经被处理，不会再传递给 onClick onLong 监听器
         */learn_button_event.setOnTouchListener { view, event ->
            Log.e(tag, "触摸事件触发" + event.action)
            false
        }
    }

    private fun get_input_content() {
        val get_input_text = findViewById<Button>(R.id.get_input_text)
        get_input_text.setOnClickListener {
            val learn_edit_text = findViewById<TextView>(R.id.learn_edit_text)
            val text = learn_edit_text.text.toString()
            Log.e(tag, "获取输入框内容：$text")
        }
    }

    private fun show_hidden_process_bar() {
        val get_process_bar_btn = findViewById<Button>(R.id.get_process_bar_btn)
        get_process_bar_btn.setOnClickListener {
            val learn_process_bar = findViewById<TextView>(R.id.get_process_bar_btn)
            val learn_progress_bar = findViewById<ProgressBar>(R.id.learn_progress_bar)
            if (learn_progress_bar.visibility == View.VISIBLE) {
                learn_progress_bar.visibility = View.GONE
            } else {
                learn_progress_bar.visibility = View.VISIBLE
            }
        }
    }

    private fun get_process_bar_process() {
        val get_process_bar_process_btn = findViewById<Button>(R.id.get_process_bar_process_btn)
        get_process_bar_process_btn.setOnClickListener {
            val learn_process_bar =
                findViewById<ProgressBar>(R.id.learn_progress_bar_process)
            var process = learn_process_bar.progress
            process += 10
            learn_process_bar.progress = process
        }
    }

    private fun send_notice() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestNotificationPermission(this)
        }

        // 获取句柄
        manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // 安卓 8 以上需要创建通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("mini_ocean", "测试通知", NotificationManager.IMPORTANCE_HIGH)
            manager!!.createNotificationChannel(channel)
        }


        // 创建跳转意图
        val intent = Intent(this, NoticeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "mini_ocean")
            .setContentTitle("通知标题")
            .setContentText("这是一条测试通知") // 通知图标必须是 alpha 绘制的图片
            .setSmallIcon(R.mipmap.ic_launcher) // 设置小图标颜色
            .setColor(Color.parseColor("#FF0000"))
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    getResources(),
                    R.drawable.image_view_100_100
                )
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        val send_notice_btn = findViewById<Button>(R.id.send_notice_btn)
        val hide_notice_btn = findViewById<Button>(R.id.hide_notice_btn)
        send_notice_btn.setOnClickListener {
            manager!!.notify(1, notification)
            Log.e(tag, "发送通知")
        }
        hide_notice_btn.setOnClickListener {
            manager!!.cancel(1)
            Log.e(tag, "取消通知")
        }
    }

    private fun toolbar_click() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setOnClickListener { Log.e(tag, "点击了工具栏") }
    }

    @SuppressLint("InflateParams")
    private fun show_dialog() {
        val show_dialog_btn = findViewById<Button>(R.id.show_dialog_btn)
        show_dialog_btn.setOnClickListener {
            val dialog_view = layoutInflater.inflate(R.layout.dialog_view, null)
            val builder = AlertDialog.Builder(this@BaseActivity)
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setTitle("这是一个对话框")
            builder.setMessage("这是对话框的内容")
            builder.setView(dialog_view)
            builder.setPositiveButton(
                "确定"
            ) { dialogInterface, i -> Log.e(tag, "点击了确定按钮") }
            builder.setNegativeButton(
                "取消"
            ) { dialogInterface, i -> Log.e(tag, "点击了取消按钮") }
            builder.setNeutralButton(
                "忽略"
            ) { dialogInterface, i -> Log.e(tag, "点击了忽略按钮") }
            builder.create()
            builder.show()
        }
    }

    @SuppressLint("InflateParams", "UseCompatLoadingForDrawables")
    private fun show_pop_window() {
        val show_pop_window_btn = findViewById<Button>(R.id.show_pop_window_btn)
        show_pop_window_btn.setOnClickListener { v ->
            val pop_view = layoutInflater.inflate(R.layout.dialog_view, null)
            val sure = pop_view.findViewById<Button>(R.id.pop_window_sure)
            val popupWindow = PopupWindow(
                pop_view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            popupWindow.setBackgroundDrawable(
                getResources().getDrawable(
                    R.drawable.ic_launcher_background,
                    null
                )
            )
            popupWindow.showAsDropDown(v, v.width, -v.height)
            // 是否可点击 popupWindow 里面
            popupWindow.isTouchable = true
            // 是否可点击 popupWindow 外面
            popupWindow.isOutsideTouchable = true
            sure.setOnClickListener {
                Log.e(tag, "点击了确定按钮")
                popupWindow.dismiss()
            }
        }
    }
}