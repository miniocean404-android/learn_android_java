package com.example.learnandroidjava.component.logic.notification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.learnandroidjava.component.logic.notification.intl.NotificationViewModel
import com.example.learnandroidjava.vm.HomeVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> NotificationComponent(vm: T) where T : ViewModel, T : NotificationViewModel {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x22149ee7))
            .height(45.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(text = "最新活动", color = Color(0xff149ee7), fontSize = 14.sp)

        Spacer(modifier = Modifier.width(8.dp))

        val state = rememberPagerState(initialPage = 0) { vm.notifications.size }

        val isDragged by state.interactionSource.collectIsDraggedAsState()

        if (!isDragged) {
            with(state) {
                var currentPageKey by remember { mutableIntStateOf(0) }
                // key1 发生变化时候才会重新执行 launch,类似 useEffect
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = 3000L)
                        val nextPage = (currentPage + 1)
                        animateScrollToPage(page = currentPage + 1)
                        currentPageKey = nextPage
                    }
                }
            }
        }


        VerticalPager(
            state = state,
            modifier = Modifier
                .weight(1f)
                .height(22.5.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = vm.notifications[it],
                color = Color(0xff333333),
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "更多", color = Color(0xff666666), fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationComponentPreview() {
    NotificationComponent(HomeVM())
}


