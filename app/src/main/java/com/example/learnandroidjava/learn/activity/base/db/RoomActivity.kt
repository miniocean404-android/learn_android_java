package com.example.learnandroidjava.learn.activity.base.db

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.databinding.ActivityRoomBinding
import com.example.learnandroidjava.learn.db.entity.Student
import com.example.learnandroidjava.learn.db.manager.DBEngine

class RoomActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG: String? = RoomActivity::class.simpleName
    private val binding: ActivityRoomBinding by lazy {
        ActivityRoomBinding.inflate(layoutInflater)
    }

    private val dbEngine by lazy {
        DBEngine(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnQueryDb.setOnClickListener(this)
        binding.btnInsertDb.setOnClickListener(this)
        binding.btnUpdateDb.setOnClickListener(this)
        binding.btnDeleteDb.setOnClickListener(this)
        binding.btnDeleteAllDb.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnQueryDb -> {
                dbEngine.queryAll()
            }

            binding.btnInsertDb -> {
                val student1 = Student("张三", 18)
                val student2 = Student("李四", 20)
                val student3 = Student("王五", 22)

                dbEngine.insert(student1, student2, student3)
            }

            binding.btnUpdateDb -> {
                val student1 = Student("李四", 999)
                student1.id = 2
                dbEngine.update(student1)
            }

            binding.btnDeleteDb -> {
                val student1 = Student()
                student1.id = 1
                dbEngine.delete(student1)
            }

            binding.btnDeleteAllDb -> {
                dbEngine.deleteAll()
            }
        }
    }
}