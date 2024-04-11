package com.example.learnandroidjava.learn.activity.base.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.learnandroidjava.R
import com.example.learnandroidjava.learn.fragment.ReplaceFragment
import com.example.learnandroidjava.learn.fragment.UseFragment
import com.example.learnandroidjava.learn.shared.intel.IFragmentActivityCommunicate

class UseFragmentActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_use_fragment)

        val changeBtn = findViewById<Button>(R.id.use_fragment_change_btn)
        val replaceBtn = findViewById<Button>(R.id.use_fragment_replace_btn)
        changeBtn.setOnClickListener(this)
        replaceBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.use_fragment_change_btn -> {
                // activity 与 fragment 通信
                val bundle = Bundle().apply {
                    putString("message", "我是 activity 传递的数据")
                }
                val fragment = UseFragment()
                fragment.arguments = bundle


                // fragment 与 activity 通信
                // 其他方案 eventBus LiveData
                fragment.setCallback(object : IFragmentActivityCommunicate {
                    override fun sendToActivity(message: String) {
                        Toast.makeText(this@UseFragmentActivity, message, Toast.LENGTH_SHORT).show()
                    }

                    override fun getFromActivity(message: String): String {
                        return "从 Fragment 主动接收的消息：$message"
                    }
                })

                replaceFragment(fragment)
            }

            R.id.use_fragment_replace_btn -> {
                replaceFragment(ReplaceFragment())
            }
        }
    }

    // 动态添加 fragment 到 layout_fragment
    private fun replaceFragment(fragment: Fragment) {
        // transaction 是开启一个事物
        val transaction = supportFragmentManager.beginTransaction()
        // 开启 layout_fragment 路由栈
        transaction.addToBackStack(null)
        // 提交事物
        transaction.replace(R.id.use_fragment_layout, fragment).commit()
    }
}

