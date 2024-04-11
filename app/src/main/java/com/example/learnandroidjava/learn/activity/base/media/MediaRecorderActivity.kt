package com.example.learnandroidjava.learn.activity.base.media

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.databinding.ActivityMediaRecorderBinding
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hjq.toast.Toaster

class MediaRecorderActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityMediaRecorderBinding by lazy {
        ActivityMediaRecorderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        XXPermissions.with(this)
            .permission(Permission.RECORD_AUDIO)
            .permission(Permission.CAMERA)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        Toaster.show("${permissions}} 权限被拒绝")
                        Toaster.show("获取部分权限成功，但部分权限未正常授予")
                        return
                    }
                    Toaster.show("获取权限成功")
                }

                override fun onDenied(permissions: List<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        Toaster.show("被永久拒绝授权，请手动授予权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@MediaRecorderActivity,
                            permissions
                        )
                    } else {
                        Toaster.show("获取权限失败")
                    }
                }
            })

        binding.record.setOnClickListener(this)
        binding.playVideo.setOnClickListener(this)
        binding.videoView.setOnClickListener(this)
        binding.playAudio.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onClick(view: View?) {
        when (view) {
            binding.record -> {
                startActivity(Intent(this, RecordActivity::class.java))
            }
            binding.playVideo -> {
                startActivity(Intent(this, MediaPlayerActivity::class.java))
            }
            binding.playAudio -> {
                startActivity(Intent(this, SoundPoolActivity::class.java))
            }
            binding.videoView -> {
                startActivity(Intent(this, VideoViewActivity::class.java))
            }
        }
    }
}