package com.example.learnandroidjava.activity.base.media

import android.media.SoundPool
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnandroidjava.R
import com.example.learnandroidjava.databinding.ActivitySoundPoolBinding
import com.example.learnandroidjava.shared.adapter.SoundPoolAdapter
import com.example.learnandroidjava.model.bean.Sound

class SoundPoolActivity : AppCompatActivity(), SoundPoolAdapter.OnItemClickListener {
    private var data: List<Sound>? = null
    private val binding: ActivitySoundPoolBinding by lazy {
        ActivitySoundPoolBinding.inflate(layoutInflater)
    }
    private val soundPool = SoundPool.Builder().setMaxStreams(2).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
            binding.recyclerView.layoutManager = this
        }

        data = listOf(
            Sound("sound1", soundPool.load(this, R.raw.sound1, 1)),
            Sound("sound2", soundPool.load(this, R.raw.sound2, 2))
        )

        binding.recyclerView.adapter = SoundPoolAdapter(this, binding.recyclerView, data!!).apply {
            setOnItemClickListener(this@SoundPoolActivity)
        }
    }

    override fun onItemClick(position: Int) {
        val id = data?.get(position)?.id
        soundPool.play(id!!, .5f, .5f, 1, 0, 1.0f)
    }

    override fun onDestroy() {
        super.onDestroy()
        for (sound in data!!) {
            soundPool.unload(sound.id)
        }
        soundPool.release()
    }
}