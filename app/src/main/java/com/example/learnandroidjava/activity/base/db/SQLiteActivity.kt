package com.example.learnandroidjava.activity.base.db

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroidjava.databinding.ActivitySqliteBinding
import com.example.learnandroidjava.shared.utils.SQLiteHelper
import kotlin.math.log

class SQLiteActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG: String? = SQLiteActivity::class.simpleName
    private val binding: ActivitySqliteBinding by lazy {
        ActivitySqliteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCreateDb.setOnClickListener(this)
        binding.btnQueryDb.setOnClickListener(this)
        binding.btnInsertDb.setOnClickListener(this)
        binding.btnUpdateDb.setOnClickListener(this)
        binding.btnDeleteDb.setOnClickListener(this)
    }

    @SuppressLint("Range")
    override fun onClick(view: View?) {
        when (view) {
            binding.btnCreateDb -> {
                val helper = SQLiteHelper.getInstant(this)
                // 使用 writableDatabase 或 readableDatabase 会执行 helper 的 onCreate 方法
                helper?.readableDatabase
            }

            binding.btnQueryDb -> {
                val helper = SQLiteHelper.getInstant(this)
                val db = helper?.readableDatabase

                Log.i(TAG, "onClick: ${db?.isOpen}")

                if (db?.isOpen == true) {
                    val cursor = db.rawQuery("select * from user", null)
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            val id = cursor.getInt(cursor.getColumnIndex("_id"))
                            val name = cursor.getString(cursor.getColumnIndex("name"))
                            val password = cursor.getString(cursor.getColumnIndex("password"))
                            Log.i(TAG, "onClick:  id = $id, name = $name, password = $password")
                        }
                    }
                    cursor?.close()
                    db.close()
                }
            }

            binding.btnInsertDb -> {
                val helper = SQLiteHelper.getInstant(this)
                val db = helper?.writableDatabase

                if (db?.isOpen == true) {
                    db.execSQL("insert into user(name, password) values('admin', '123456')")
                    db.close()
                }
            }

            binding.btnUpdateDb -> {
                val helper = SQLiteHelper.getInstant(this)
                val db = helper?.writableDatabase

                if (db?.isOpen == true) {
                    db.execSQL("update user set name = ? where _id = ?", arrayOf("admin_update", 1))
                    db.close()
                }
            }

            binding.btnDeleteDb -> {
                val helper = SQLiteHelper.getInstant(this)
                val db = helper?.writableDatabase

                if (db?.isOpen == true) {
                    db.execSQL("delete from user where _id = ?", arrayOf(3))
                    db.close()
                }
            }
        }
    }
}