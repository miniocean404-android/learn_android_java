package com.example.learnandroidjava.shared.local

import androidx.compose.runtime.compositionLocalOf
import com.example.learnandroidjava.vm.HomeVM

val LocalHomeVm = compositionLocalOf<HomeVM> { error("HomeVM 上下文没有找到") }