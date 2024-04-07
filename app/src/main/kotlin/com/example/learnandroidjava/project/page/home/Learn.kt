package com.example.learnandroidjava.project.page.home


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.learnandroidjava.project.component.learn.CanvasLineChart
import com.example.learnandroidjava.project.component.learn.CanvasRings
import com.example.learnandroidjava.project.component.learn.GetScreenDp
import com.example.learnandroidjava.project.component.learn.StatusBarsPadding
import com.example.learnandroidjava.project.component.learn.TextUse
import com.example.learnandroidjava.project.component.common.custom_app_bar.CustomTopAppBar
import com.example.learnandroidjava.project.component.logic.notification.NotificationComponent
import com.example.learnandroidjava.project.component.common.swiper.Swiper
import com.example.learnandroidjava.project.component.logic.article.ArticleCard
import com.example.learnandroidjava.project.vm.ArticleVM
import com.example.learnandroidjava.project.vm.HomeVM


@Composable
fun LearnPage(navController: NavController) {
    Home()
}

@SuppressLint("InvalidColorHexValue")
@Composable
fun Home(homeVm: HomeVM = viewModel(), articleVm: ArticleVM = ArticleVM()) {
    Column {
        CustomTopAppBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            SearchBar(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "99%", fontSize = 12.sp, color = Color.White)

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = null,
                // 设置图标颜色
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }

        TabRow(
            selectedTabIndex = homeVm.categoryIndex,
            containerColor = MaterialTheme.colorScheme.secondary,
        ) {
            homeVm.categories.forEachIndexed() { index, category ->
                Tab(
                    selected = homeVm.categoryIndex == index,
                    onClick = {
                        homeVm.categoryIndex = index
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(alpha = 0.5f),
                ) {
                    Text(
                        text = category.title,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 14.sp
                    )
                }
            }
        }

        TabRow(
            selectedTabIndex = homeVm.typeIndex,
            containerColor = MaterialTheme.colorScheme.secondary,
            // 隐藏指示器
            indicator = {},
            // 隐藏分割线
            divider = {},
        ) {
            homeVm.types.forEachIndexed() { index, dataType ->
                LeadingIconTab(
                    selected = homeVm.typeIndex == index,
                    onClick = {
                        homeVm.typeIndex = index
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    modifier = Modifier.background(Color.White),
                    icon = {
                        Icon(
                            imageVector = dataType.icon,
                            contentDescription = null,
                            // 设置图标颜色
                            // tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    text = {
                        Text(
                            text = dataType.title,
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 14.sp,
                        )
                    }
                )
            }
        }

        if (homeVm.typeIndex == 0) {
            LazyColumn {
                item {
                    Swiper(homeVm)
                }

                item {
                    NotificationComponent(homeVm)
                }

                items(articleVm.newsList) { article ->
                    ArticleCard(article)
                }


                item {
                    StatusBarsPadding()
                    TextUse()
                    GetScreenDp()
                    CanvasRings()
                    CanvasLineChart()
                    LinearProgressIndicator(
                        0.2f,
                        strokeCap = StrokeCap.Round
                    )

                    OutlinedButton(
                        onClick = {},
                        border = BorderStroke(1.dp, Color.Black),
                        shape = CircleShape,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Black,
                        ),
                        enabled = true
                    ) {
                        Text("OutlinedButton")
                    }
                }
            }
        } else {
            Text(text = "视频列表")
        }


    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .then(modifier),
        color = Color(0x33ffffff)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                // 设置图标颜色
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                "搜索",
                color = Color.White,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LearnPagePreview() {
    Home()
}