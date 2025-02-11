package com.example.learnandroidjava.page.webview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnandroidjava.component.common.video.VideoAndroidViewComponent
import com.example.learnandroidjava.component.common.webview.rememberWebViewState
import com.example.learnandroidjava.component.common.video.rememberVideoController
import com.example.learnandroidjava.component.learn.GetScreenDp
import com.example.learnandroidjava.component.learn.InputLearn
import com.example.learnandroidjava.component.learn.SkeletonScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun WebViewPage(navController: NavController) {
    // remember 代表界面刷新时候记住这个状态值
    var fontScale by remember {
        // 类似 useState
        mutableFloatStateOf(1.0f)
    }
    val state = rememberWebViewState(url = "https://www.baidu.com")
    val scaleState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    val videoController = rememberVideoController()
//    LaunchedEffect(videoController) {
//        videoController.start(
//            "http://stream4.iqilu.com/ksd/video/2020/02/17/87d03387a05a0e8aa87370fb4c903133.mp4"
//        )
//    }


    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        refreshing = false
    }

    // 用与 lazyColumn
    rememberLazyListState()

    val refreshState = rememberPullRefreshState(refreshing, ::refresh)

    BottomSheetScaffold(
        scaffoldState = scaleState,
        sheetContent = {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "字体大小", fontSize = 16.sp)
                Slider(value = fontScale, onValueChange = {
                    fontScale = it
                    state.evaluateJavascript("document.body.style.zoom = '${fontScale}';")
                }, steps = 3, valueRange = 0.75f..1.75f)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "较小", fontSize = 14.sp)
                    Text(text = "默认", fontSize = 14.sp)
                    Text(text = "较大", fontSize = 14.sp)
                    Text(text = "超大", fontSize = 14.sp)
                    Text(text = "特大", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "WebViewPage",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.NavigateBefore,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                navController.navigateUp()
                            }
                            .padding(8.dp)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.TextFields,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                scope.launch {
                                    scaleState.bottomSheetState.expand()
                                    if (scaleState.bottomSheetState.currentValue == SheetValue.PartiallyExpanded) {
                                        scaleState.bottomSheetState.show()
                                    } else {
                                        scaleState.bottomSheetState.hide()
                                    }
                                }
                            }
                            .padding(8.dp)
                    )
                }
            )
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .statusBarsPadding(),
//        sheetPeekHeight = 0.dp,
    ) {
        Box(Modifier.pullRefresh(refreshState)) {
            // PullRefreshIndicator 必须要有 LazyColumn 这种可滚动区域
            PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))

            Column {
                // loading 图标
                CircularProgressIndicator()
                GetScreenDp()
                // VideoAndroidViewComponent(videoController.player)
                // WebViewComponent(state = state)
                // InputLearn()
                SkeletonScreen()
            }
        }

    }
}