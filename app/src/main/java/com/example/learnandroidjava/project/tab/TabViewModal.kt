package com.example.learnandroidjava.project.tab

import android.app.Application
import androidx.databinding.ObservableField
import com.miniocean.base.BaseViewModel

class TabViewModal(application: Application) : BaseViewModel(application) {
    val text = ObservableField<String>()
}