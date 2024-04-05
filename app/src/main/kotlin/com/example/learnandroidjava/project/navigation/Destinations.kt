package com.example.learnandroidjava.project.navigation


// sealed 代表只能被其内部定义的子类继承，而且这些子类必须位于同一个文件中
sealed class Destinations(val route: String) {
    object home : Destinations("home")
    object detail : Destinations("detail")
}