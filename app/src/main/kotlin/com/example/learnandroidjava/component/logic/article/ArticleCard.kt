package com.example.learnandroidjava.component.logic.article

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.learnandroidjava.modal.entity.ArticleData

@Composable
fun ArticleCard(data: ArticleData) {

    val constraintSet = ConstraintSet {
        val image = createRefFor("image")
        val title = createRefFor("title")
        val source = createRefFor("source")
        val timestamp = createRefFor("timestamp")

        constrain(image) {
            start.linkTo(parent.start)
            centerVerticallyTo(parent)
            width = Dimension.value(80.dp)
        }

        constrain(title) {
            start.linkTo(image.end , margin = 8.dp)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(source) {
            start.linkTo(title.start)
            bottom.linkTo(parent.bottom)
        }
        constrain(timestamp) {
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
    }

    ConstraintLayout(constraintSet) {
        AsyncImage(
            model = data.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .layoutId("image")
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop
        )

        Text(
            text = data.title,
            fontSize = 16.sp,
            color = Color(0xff333333),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("title")
        )

        Text(
            text = "来源: ${data.source}",
            fontSize = 10.sp,
            maxLines = 1,
            color = Color(0xff999999),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 8.dp)
                .layoutId("source"),
        )

        Text(
            text = "时间: ${data.timestamp}",
            fontSize = 10.sp,
            maxLines = 1,
            color = Color(0xff999999),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .layoutId("timestamp"),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider()
    }
}


@Composable
@Preview
fun ArticleCardPreview() {
    ArticleCard(ArticleData("title", "source", "timestamp", ""))
}