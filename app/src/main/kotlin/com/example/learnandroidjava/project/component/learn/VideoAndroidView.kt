package com.example.learnandroidjava.project.component.learn

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.LinearLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.learnandroidjava.R
import com.example.learnandroidjava.databinding.ActivityIjkplayerBinding
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import java.io.IOException

@SuppressLint("InflateParams")
@Composable
fun VideoAndroidViewComponent() {
    val player = IjkMediaPlayer()
    val url =
        "http://stream4.iqilu.com/ksd/video/2020/02/17/87d03387a05a0e8aa87370fb4c903133.mp4"

    LaunchedEffect(player) {
        try {
            player.dataSource = url
            player.prepareAsync()
            player.start();
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    // LocalContext.current === xml 的 context
    // android view 可以引用 xml 写的布局
    AndroidView(factory = { context ->
        val layout =
            LayoutInflater.from(context).inflate(R.layout.activity_compose_ijkplayer, null, false)
        // 获取 bind 写法
        // val binding: ActivityIjkplayerBinding = ActivityIjkplayerBinding.bind(layout)


        val view = layout.findViewById<LinearLayout>(R.id.main)

        // 可以直接返回 findViewById  获取的 surface_view，但是界面布局只能是下方
        // <?xml version="1.0" encoding="utf-8"?>
        // <SurfaceView xmlns:android="http://schemas.android.com/apk/res/android"
        // android:id="@+id/surface_view"
        // android:layout_width="match_parent"
        // android:layout_height="500dp"/>
        layout.findViewById<SurfaceView>(R.id.surface_view).apply {
            holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceCreated(holder: SurfaceHolder) {
                    player.setDisplay(this@apply.holder)
                }

                override fun surfaceChanged(
                    holder: SurfaceHolder,
                    format: Int,
                    width: Int,
                    height: Int
                ) {
                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {
                }
            })
        }

        view
    })
}