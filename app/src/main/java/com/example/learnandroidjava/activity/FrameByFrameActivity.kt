package com.example.learnandroidjava.activity

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.R

class FrameByFrameActivity : AppCompatActivity() {

    private var flag = true;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animation_frame_by_frame)

        val layout = findViewById<RelativeLayout>(R.id.animation_frame_by_frame)
        val animation = layout.background as AnimationDrawable

        layout.setOnClickListener {
            if (flag){
                flag = false
                animation.start()
            }else{
                animation.stop()
                flag = true
            }
        }
    }
}