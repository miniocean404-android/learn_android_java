package com.example.learnandroidjava.intel

interface IFragmentCallback {
    fun sendToActivity(message: String)
    fun getFromActivity(message: String):String
}