package com.example.learnandroidjava.activity.base.anim

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.R

class TweenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.animation_tween)

        val image = findViewById<ImageView>(R.id.tween)
        image.setOnClickListener{
//            val animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
//            val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
//            val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate)
            image.startAnimation(animation)
        }
    }
}