package com.example.learnandroidjava.activity.base.media

import android.hardware.Camera
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.view.Surface
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnandroidjava.R
import com.example.learnandroidjava.databinding.ActivityRecordBinding
import com.example.learnandroidjava.shared.constant.DOWNLOAD
import com.hjq.toast.Toaster
import java.io.File
import java.io.IOException

@RequiresApi(Build.VERSION_CODES.S)
class RecordActivity : AppCompatActivity(), View.OnClickListener {
    //
    private var camera: Camera?= null;

    private var recorder: MediaRecorder? = null

    private val binding: ActivityRecordBinding by lazy {
        ActivityRecordBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRecord.setOnClickListener(this)
        binding.btnStop.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnRecord -> {
                camera = Camera.open().apply {
                    setDisplayOrientation(90)
                    unlock()
                }

                recorder =  MediaRecorder(this).apply {
                    // init
                    setCamera(camera)
                    setAudioSource(MediaRecorder.AudioSource.MIC) // 设置音频源
                    setVideoSource(MediaRecorder.VideoSource.CAMERA) // 设置视频源

                    // 数据源配置
                    setOutputFormat(MediaRecorder.OutputFormat.MPEG_4) // 设置视频格式
                    setAudioEncoder(MediaRecorder.AudioEncoder.AAC) // 设置音频编码
                    setVideoEncoder(MediaRecorder.VideoEncoder.H264) // 设置视频编码

                    setOrientationHint(90) // 设置视频旋转角度

                    // getExternalFilesDir：android/包名下的目录
                    // getExternalStorageDirectory：android 根目录

                    setOutputFile(File(DOWNLOAD, "/test.mp4").absolutePath) // 设置文件输出路径
                    setVideoSize(1280, 720) // 设置视频尺寸
                    setVideoFrameRate(30) // 设置视频帧率
                    setPreviewDisplay(Surface(binding.textureView.surfaceTexture)) // 设置预览视视频
                }


                try {
                    // 准备就绪
                    recorder!!.prepare()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                // 开始录制
                recorder!!.start()
            }

            binding.btnStop -> {
                try {
                    // 停止录制
                    recorder!!.stop()
                    // 释放资源
                    recorder!!.release()

                    camera!!.stopPreview()
                    camera!!.release()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}