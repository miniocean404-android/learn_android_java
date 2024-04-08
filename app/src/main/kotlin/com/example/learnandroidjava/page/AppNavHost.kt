package com.example.learnandroidjava.page

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.learnandroidjava.modal.entity.NavigationItem
import com.example.learnandroidjava.navigation.Router
import com.example.learnandroidjava.page.home.LearnPage
import com.example.learnandroidjava.page.home.MinePage
import com.example.learnandroidjava.page.home.TaskPage
import com.example.learnandroidjava.page.webview.WebViewPage


// sealed 代表只能被其内部定义的子类继承，而且这些子类必须位于同一个文件中
// Navigation Compose文章： https://juejin.cn/post/7322789648601366579?searchId=2024040722153417099C92F4152B2A2EFA
// 仓库：https://github.dev/yaoxiawen/ComposeNavigationDemo


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    val bottomItems = listOf(
        NavigationItem(Router.LEARN, "学习", Icons.Filled.Home),
        NavigationItem(Router.TASK, "任务", Icons.Filled.DateRange),
        NavigationItem(Router.MINE, "我的", Icons.Filled.Person),
    )

    //获取当前的 NavBackStackEntry 来访问当前的 NavDestination
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    // Compose M3 1.1.0 后 Scaffold、AppTopBar 、AppBottom 默认添加了 WindowInsets 支持，使用时不需要再特别处理 WindowInsets
    // ProvideWindowInsets 与 navigationBarsPadding 用于处理状态栏和导航栏的 padding
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                bottomItems.forEachIndexed { _, screen ->
                    if (currentDestination != null) {
                        NavigationBarItem(
                            selected = currentDestination.hierarchy.any { it.route == screen.route },
                            onClick = {
                                //加这个可解决问题：按back键会返回2次，第一次先返回home, 第二次才会退出
                                // navController.popBackStack()

                                //点击item时，清空栈内 popUpTo ID到栈顶之间的所有节点，避免站内节点持续增加
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        //跳转时保存页面状态
                                        saveState = true
                                    }
                                    //栈顶复用，避免重复点击同一个导航按钮，回退栈中多次创建实例
                                    launchSingleTop = true
                                    //回退时恢复页面状态
                                    restoreState = true
                                    //通过使用 saveState 和 restoreState 标志，当在底部导航项之间切换时，
                                    //系统会正确保存并恢复该项的状态和返回堆栈。
                                }
                            },
                            label = {
                                Text(text = screen.title)
                            },
                            icon = {
                                Icon(
                                    imageVector = screen.icon,
                                    contentDescription = null
                                )
                            },
                        )
                    }
                }
            }
        }) {

        NavHost(
            navController = navController,
            // 启动页
            startDestination = Router.LEARN
        ) {
            /**
             * composable("${Router.Home}/name/age?user={user}&password={password}")  /name/age 代表必传参数
             * 可选参数，使用"?argName={argName}&argName2={argName2}"拼接，跟浏览器地址栏的可选参数一样，第一个用?拼接，后续用&拼接
             */
            /**
             * composable("${Router.Home}/name/age?user={user}&password={password}")  /name/age 代表必传参数
             * 可选参数，使用"?argName={argName}&argName2={argName2}"拼接，跟浏览器地址栏的可选参数一样，第一个用?拼接，后续用&拼接
             */
            /**
             * composable("${Router.Home}/name/age?user={user}&password={password}")  /name/age 代表必传参数
             * 可选参数，使用"?argName={argName}&argName2={argName2}"拼接，跟浏览器地址栏的可选参数一样，第一个用?拼接，后续用&拼接
             */

            /**
             * composable("${Router.Home}/name/age?user={user}&password={password}")  /name/age 代表必传参数
             * 可选参数，使用"?argName={argName}&argName2={argName2}"拼接，跟浏览器地址栏的可选参数一样，第一个用?拼接，后续用&拼接
             */
            composable(
                Router.LEARN,
                enterTransition = {
                    slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                }
//                arguments = listOf(
//                    // 参数是String类型可以不用额外指定
//                    navArgument("name") {},
//                    navArgument("age") {
//                        type = NavType.IntType
//                        defaultValue = 25 //默认值（选配）
//                        nullable = true  // 可否为null（选配）
//                    },
//                ),
            ) {

                // 通过composable函数中提供的NavBackStackEntry提取参数
                // val argument = requireNotNull(it.arguments)
                // val name = argument.getString("name")
                // val age = argument.getInt("age")

                LearnPage(navController)
            }

            composable(
                Router.TASK,
                enterTransition = {
                    slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                }
            ) {
                TaskPage(navController)
            }

            composable(
                Router.MINE,
                enterTransition = {
                    slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                }
            ) {
                MinePage(navController)
            }

            composable(
                Router.WEB_VIEW,
                enterTransition = {
                    slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                }
            ) {
                WebViewPage(navController)
            }

            /**
             * 深度链接 DeepLink
             * 默认情况下，深度链接不会向外部应用公开。如需向外部提供这些深层链接，必须向应用的 manifest.xml 文件添加相应的元素。在清单的元素中添加以下内容：
             * <activity …>
             *   <intent-filter>
             *     ...
             *     <data android:scheme="my-app" android:host="my.example.app" />
             *   </intent-filter>
             * </activity>
             */
            composable(
                Router.DEEP_LINK,
                deepLinks = listOf(navDeepLink { uriPattern = "${Router.URI}/{params1}/{params2}" })
            ) {
                //通过composable函数中提供的NavBackStackEntry提取参数
                val argument = requireNotNull(it.arguments)
                val params1 = argument.getString("params1")
                val params2 = argument.getInt("params2")
                // DeepLinkPage(navController)
            }
        }

        // 解决使用纵向滚动时候元素被隐藏的问题
        // Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
        //     when (bottomIndex) {
        //         0 -> LearnPage()
        //         1 -> TaskPage()
        //         2 -> MinePage(currentDestination)
        //     }
        // }
    }
}