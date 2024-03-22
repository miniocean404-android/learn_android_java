package com.example.learnandroidjava.activity

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
    private val url:String = "http://stream4.iqilu.com/ksd/video/2020/02/17/87d03387a05a0e8aa87370fb4c903133.mp4"

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