package com.example.learnandroidjava.activity.base.api

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amap.api.services.auto.ListData.Content
import com.example.learnandroidjava.R
import com.example.learnandroidjava.databinding.ActivitySharePreferencesBinding
import com.hjq.toast.ToastParams
import com.hjq.toast.Toaster

/**
 * 会存储到手机包中的 shared_prefs 中，是 xml 文件
 * 只存储少量数据
 *
 * 默认 MODE_PRIVATE 会覆盖 ，MODE_APPEND 会追加
 */
class SharePreferencesActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivitySharePreferencesBinding by lazy {
        ActivitySharePreferencesBinding.inflate(layoutInflater)
    }

    private val sp: SharedPreferences by lazy {
        getSharedPreferences("mini_ocean", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        restoreView()
    }


    private fun initView() {
        binding.login.setOnClickListener(this)
        binding.register.setOnClickListener(this)
    }

    private fun restoreView() {
        val isRememberPassword = sp.getBoolean("remember_password", false)
        val isAutoLogin = sp.getBoolean("auto_login", false)

        if (isRememberPassword) {
            val name = sp.getString("username", "")
            val pwd = sp.getString("password", "")
            binding.name.setText(name)
            binding.pwd.setText(pwd)
            binding.rememberPwd.isChecked = true
        }

        if (isAutoLogin) {
            binding.autoLogin.isChecked = true
            binding.login.performClick()
            Toaster.show("自动登录了")
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.login -> {
                val name = binding.name.text.toString().trim()
                val pwd = binding.pwd.text.toString().trim()
                val isRemember = binding.rememberPwd.isChecked
                val isAuto = binding.autoLogin.isChecked

                if (name.isEmpty() || pwd.isEmpty()) {
                    Toaster.show("请输入用户名和密码")
                } else {
                    if (isRemember) {
                        sp.edit().putString("username", name).apply()
                        sp.edit().putString("password", pwd).apply()
                        sp.edit().putBoolean("remember_password", true).apply()
                    }

                    if (isAuto) {
                        sp.edit().putBoolean("auto_login", true).apply()
                    }

                    Toaster.show("登录成功")
                }
            }

            R.id.register -> {
            }
        }
    }
}