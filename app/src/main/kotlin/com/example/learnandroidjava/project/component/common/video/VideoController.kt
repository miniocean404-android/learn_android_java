package com.example.learnandroidjava.project.component.common.video

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import java.io.IOException

class VideoController {
    val player = IjkMediaPlayer()

    fun start(url: String) {
        try {
            player.dataSource = url
            player.prepareAsync()
            player.start();
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun stop() {
        player.stop()
        player.release()
    }

    fun pause() {
        player.pause()
    }

    fun resume() {
        player.start()
    }

    fun seekTo(sec: Long) {
        player.seekTo(sec)
    }
}

@Composable
fun rememberVideoController() = remember {
    VideoController()
}