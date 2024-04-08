package com.example.learnandroidjava.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController

object Router {
    // deep link URI
    val URI = "my-app://my.example.app"

    const val LEARN: String = "learn"
    const val MINE: String = "mine"
    const val TASK: String = "task"


    const val WEB_VIEW: String = "web_view"
    const val DEEP_LINK: String = "deep_link"

    fun jump(navController: NavController, to: String) {
        navController.navigate(to)
    }

    fun back(navController: NavController) {
        navController.navigateUp()    //返回上一级界面
        // navController.popBackStack()  //可以指定返回的界面（不指定就相当于navigateUp()）。
    }

    /**
     * 传入一个目的地，在跳转新页面之前会将页面堆栈中直到目的地的所有可组合项弹出，然后跳转新页面。
     * 当前页面堆栈是 1、2，会弹出直到 1 之前的所有可组合项，也就是会把 2 弹出，页面堆栈只剩下 1，然后将新页面 3 加入页面堆栈中，并跳转 3。最终页面堆栈是 1、3。
     */
    fun popUpTo(navController: NavController, to: String, before: String) {
        navController.navigate(to) {
            popUpTo(before) {
                // 跳转时是否保存页面状态
                saveState = false
            }
        }
    }

    /**
     * 配置 inclusive = true 会将目的地也弹出
     * 清空页面堆栈，然后将新页面 3 加入页面堆栈中，并跳转 3。
     */
    fun redirect(navController: NavController, to: String, before: String) {
        navController.navigate(to) {
            popUpTo(before) { inclusive = true }
        }
    }

    /**
     * 对应 Android 的 SingleTop，如果导航的栈顶部已经是 to，就不会重新创建
     */
    fun jumpTop(navController: NavController, to: String) {
        navController.navigate(to) {
            launchSingleTop = true
        }
    }

    /**
     * 深度链接匹配跳转
     */
    fun JumpDeepLink(navController: NavController, to: String) {
        navController.navigate("$URI/deeplink/123".toUri())
    }
}