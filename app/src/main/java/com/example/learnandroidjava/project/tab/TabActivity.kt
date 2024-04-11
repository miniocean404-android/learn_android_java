package com.example.learnandroidjava.project.tab

import com.example.learnandroidjava.BR
import com.example.learnandroidjava.R
import com.example.learnandroidjava.databinding.ActivityTabBinding
import com.miniocean.base.BaseActivity

class TabActivity : BaseActivity<ActivityTabBinding, TabViewModal>() {
    override fun getLayoutId(): Int {
       return R.layout.activity_tab
    }

    override fun getViewModelId(): Int {
        return BR.tabVm
    }

    override fun initViewData() {
    }
}