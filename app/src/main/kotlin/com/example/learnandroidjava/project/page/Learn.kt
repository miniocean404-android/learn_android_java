package com.example.learnandroidjava.project.page


import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learnandroidjava.project.component.custom_app_bar.CustomTopAppBar
import com.example.learnandroidjava.project.component.swiper.Swiper
import com.example.learnandroidjava.project.vm.HomeViewModel


@Composable
fun LearnPage() {
    Home()

}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("InvalidColorHexValue")
@Composable
fun Home(vm: HomeViewModel = viewModel()) {
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
            selectedTabIndex = vm.categoryIndex,
            containerColor = MaterialTheme.colorScheme.secondary,
        ) {
            vm.categories.forEachIndexed() { index, category ->
                Tab(
                    selected = vm.categoryIndex == index,
                    onClick = {
                        vm.categoryIndex = index
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
            selectedTabIndex = vm.typeIndex,
            containerColor = MaterialTheme.colorScheme.secondary,
            // 隐藏指示器
            indicator = {},
            // 隐藏分割线
            divider = {},
        ) {
            vm.types.forEachIndexed() { index, dataType ->
                LeadingIconTab(
                    selected = vm.typeIndex == index,
                    onClick = {
                        vm.typeIndex = index
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

        Swiper(vm)
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