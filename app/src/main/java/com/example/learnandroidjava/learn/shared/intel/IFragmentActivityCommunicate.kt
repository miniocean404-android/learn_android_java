package com.example.learnandroidjava.learn.shared.intel

// fragment activity 通信，也可以通过 event bus
interface IFragmentActivityCommunicate {
    fun sendToActivity(message: String)
    fun getFromActivity(message: String):String
}