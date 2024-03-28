package com.example.learnandroidjava.activity.base.media

import android.media.MediaPlayer
import android.os.Bundle
import android.view.Surface
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.databinding.ActivityMediaPlayerBinding
import com.example.learnandroidjava.shared.constant.DOWNLOAD
import java.io.File

class MediaPlayerActivity : AppCompatActivity(), View.OnClickListener,
    MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private val binding: ActivityMediaPlayerBinding by lazy {
        ActivityMediaPlayerBinding.inflate(layoutInflater)
    }

    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnPlayer.setOnClickListener(this)
        binding.btnPause.setOnClickListener(this)
        binding.btnStop.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnPlayer -> {
                val videoPath = File(DOWNLOAD, "test.mp4").path
//                 val videoPath = "http://stream4.iqilu.com/ksd/video/2020/02/17/87d03387a05a0e8aa87370fb4c903133.mp4"
                player = MediaPlayer().apply {
                    // 设置准备好资源的监听
                    setOnPreparedListener(this@MediaPlayerActivity)
                    // 设置播放完成监听
                    setOnCompletionListener(this@MediaPlayerActivity)

                    setDataSource(videoPath)
                    // 设置画布
                    setSurface(Surface(binding.textureView.surfaceTexture))
                    // 异步准备
                    prepareAsync()
                }
                // player.duration
            }

            binding.btnPause -> {
                player?.pause()
                player?.release()
            }

            binding.btnStop -> {
                player?.stop()
                player?.release()
            }
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        mp?.stop()
        mp?.release()
    }
}


