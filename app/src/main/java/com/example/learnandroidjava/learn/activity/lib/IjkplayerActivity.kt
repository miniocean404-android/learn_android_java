package com.example.learnandroidjava.learn.activity.lib

import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.R
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import java.io.IOException

/**
 * 文章： https://juejin.cn/post/7094924395646287880
 */
class IjkplayerActivity : AppCompatActivity() {
    private val url:String = "https://v3-web.douyinvod.com/6b3097d828e657647c9fbae84049f489/661568fb/video/tos/cn/tos-cn-ve-15/o4POJTnhcTIaCUtzAzQEelGfyt8AWUADAz1BgZ/?a=6383&ch=5&cr=3&dr=0&lr=all&cd=0%7C0%7C0%7C3&cv=1&br=993&bt=993&cs=0&ds=4&ft=LjhJEL998xztuo0mo0P5fQhlpPiX7F~WxVJEcmseQbPD-Ipz&mime_type=video_mp4&qs=0&rc=OjU6Njo5ZTNmaTc3aTs5OUBpanRza2k6ZjxzcjMzNGkzM0BjMjExMS9jNjQxMTEzXl81YSNuZmVtcjRvNDZgLS1kLTBzcw%3D%3D&btag=e00028000&cquery=101s_100B_100H_100K_100a&dy_q=1712675397&feature_id=46a7bb47b4fd1280f3d3825bf2b29388&l=202404092309554991CDB4A0A5E0577248"

    private val tag: String = "mini_ocean"
    private lateinit var mSurfaceView: SurfaceView
    private lateinit var player: IjkMediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ijkplayer)

        player = IjkMediaPlayer()
        mSurfaceView = findViewById(R.id.surface_view)

        mSurfaceView.holder.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceCreated(holder: SurfaceHolder) {
                player.setDisplay(mSurfaceView.holder)
                Log.d(tag, "surfaceCreated: ");
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                Log.d(tag, "surfaceChanged: ");
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                Log.d(tag, "surfaceDestroyed: ");
            }
        })


        try {
            player.dataSource = url
            player.prepareAsync()
            player.start();
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        player.start()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}