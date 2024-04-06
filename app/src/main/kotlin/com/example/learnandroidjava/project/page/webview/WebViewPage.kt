package com.example.learnandroidjava.project.page.webview

import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnandroidjava.base.component.ScrollLayout
import com.example.learnandroidjava.project.component.common.WebViewComponent
import com.example.learnandroidjava.project.component.common.rememberWebViewState
import com.example.learnandroidjava.project.component.learn.VideoAndroidViewComponent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewPage() {
    var fontScale by remember {
        mutableFloatStateOf(1.0f)
    }
    val state = rememberWebViewState(url = "https://www.baidu.com")
    val scaleState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

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
            .statusBarsPadding(),
//        sheetPeekHeight = 0.dp,
    ) {
        VideoAndroidViewComponent()
        // WebViewComponent(state = state)
    }
}