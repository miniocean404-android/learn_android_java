package com.example.learnandroidjava.activity.base.media

import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroidjava.R
import com.example.learnandroidjava.databinding.ActivityVideoViewBinding
import com.example.learnandroidjava.shared.constant.DOWNLOAD
import java.io.File

class VideoViewActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityVideoViewBinding by lazy {
        ActivityVideoViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.videoView.setVideoPath(File(DOWNLOAD, "test.mp4").absolutePath)
        val controller = MediaController(this).apply {
            setPrevNextListeners(this@VideoViewActivity, this@VideoViewActivity)
        }

        binding.videoView.setMediaController(controller)
        binding.videoView.start()
    }

    // 上一首、下一首
    override fun onClick(view: View?) {
    }
}