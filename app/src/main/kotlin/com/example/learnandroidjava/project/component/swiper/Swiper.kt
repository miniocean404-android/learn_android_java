package com.example.learnandroidjava.project.component.swiper

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.learnandroidjava.project.component.swiper.intl.SwiperViewModel
import com.example.learnandroidjava.project.vm.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> Swiper(vm: T) where T : ViewModel, T : SwiperViewModel {

    val virtualNum = Int.MAX_VALUE
    val realNum = vm.swipes.size
    val middleNum = virtualNum / 2

    val state = rememberPagerState(initialPage = middleNum) { virtualNum }
    val isDragged by state.interactionSource.collectIsDraggedAsState()


    // 定时轮播, 定时器方式
    // val coroutineScope = rememberCoroutineScope() // 携程
    // DisposableEffect 函数会在初始化的时候执行，onDispose 会在组件销毁时候调用
    //    DisposableEffect(Unit) {
    //        val timer = Timer()
    //        timer.schedule(object : TimerTask() {
    //            override fun run() {
    //                coroutineScope.launch {
    //                    if (!isDragged) state.animateScrollToPage(state.currentPage + 1)
    //
    //                }
    //            }
    //        }, 2000, 2000)
    //
    //        onDispose {
    //            timer.cancel()
    //        }
    //    }

    if (!isDragged) {
        with(state) {
            var currentPageKey by remember { mutableIntStateOf(0) }
            // key1 发生变化时候才会重新执行 launch,类似 useEffect
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                    delay(timeMillis = 2000L)
                    val nextPage = (currentPage + 1)
                    animateScrollToPage(page = currentPage + 1)
                    currentPageKey = nextPage
                }
            }
        }
    }

    Box {
        HorizontalPager(
            state = state,
            // 内容的内间距（缩小大小），但是每个 swiper 是链接起来的
            contentPadding = PaddingValues(horizontal = 32.dp),
            // 内容与内容之间的间距，不会影响内容本身大小
            pageSpacing = 16.dp,
            key = { vm.swipes[it % realNum].url }
        ) {
            val actualIndex = it % realNum

            AsyncImage(
                model = vm.swipes[actualIndex].url, contentDescription = null,
                modifier = Modifier
                    .carouselTransition(it, state)
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Gray),

                contentScale = ContentScale.Crop
            )
        }

        DotIndicators(
            totalCount = realNum,
            state = state,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicators(
    totalCount: Int,
    state: PagerState,
    modifier: Modifier
) {
    Row(modifier = modifier) {
        repeat(totalCount) { iteration ->
            val color =
                if (state.currentPage % totalCount == iteration) Color.White.copy(
                    alpha = 0.3f
                ) else Color.White
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
            Spacer(modifier = Modifier.width(3.dp))
        }
    }
}

// 过渡动画
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, state: PagerState) =
    graphicsLayer {
        // state.currentPage 当前界面展示的下标 从 0 开始
        // state.currentPageOffsetFraction 当前界面展示的下标相对于 中心的偏移量 中心为 0.0 大于 0.0 为右边偏移 小于 0.0 为左边偏移
        val pageOffset =
            (state.currentPageOffsetFraction + (state.currentPage - page)).absoluteValue

        // 动画属性
        val transformation =
            // 开始到结束的值的计算 fraction 为 0.0 时候为开始值 为 1.0 时候为结束值，动态变化的为 stop - start 的值
            lerp(
                start = .85f,
                stop = 1f,
                fraction = 1 - pageOffset.coerceIn(0f, 1f)
            )

        alpha = transformation
        scaleY = transformation
    }

@Preview(showBackground = true)
@Composable
fun LearnPagePreview() {
    Swiper(HomeViewModel())
}