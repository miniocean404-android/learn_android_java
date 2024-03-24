package com.example.learnandroidjava.shared.intel

interface IFragmentCallback {
    fun sendToActivity(message: String)
    fun getFromActivity(message: String):String
}