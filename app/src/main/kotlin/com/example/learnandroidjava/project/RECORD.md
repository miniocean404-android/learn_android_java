## Compose 项目
https://github.com/TheSomeshKumar/Flixplorer

###  设置状态栏透明
```kotlin
// 1. 设置透明
SideEffect {
    val window = (view.context as Activity).window
    window.statusBarColor = Color.Transparent.toArgb()
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
}

// 2. class 中添加
enableEdgeToEdge()

// 动态设置状态栏高度 padding top 距离
```

### 获取状态栏高度
方式一： class 中
```kotlin
var statusBarHeight = 0
val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
if (resourceId > 0) statusBarHeight = resources.getDimensionPixelSize(resourceId)

// compose 中使用
val statusBarHeightDp = with(LocalDensity.current){
    statusBarHeight.toDp()
}
```
方式二：compose 中
```kotlin
val statusBarHeightDp = LocalDensity.current.run {
    WindowInsets.statusBars.getTop(this).toDp()
}
```

### 让界面显示在 导航栏和状态栏 后边，也就是导航栏状态栏遮挡住界面
```kotlin
WindowCompat.setDecorFitsSystemWindows(window, false)
```
