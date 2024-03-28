package com.example.learnandroidjava.shared.constant

import android.os.Environment.DIRECTORY_DOWNLOADS
import android.os.Environment.getExternalStorageDirectory
import android.os.Environment.getExternalStoragePublicDirectory
import java.io.File


val ROOT = File(getExternalStorageDirectory(), "").absolutePath
val DOWNLOAD = File(getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS),"").absolutePath
