package com.example.newsapplication.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapplication.model.News.NewsItem
import com.example.newsapplication.ui.theme.gray

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(newsData: NewsItem, navController: NavHostController, onNewsItemClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable {
                onNewsItemClick()
            },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {

        GlideImage(
            model = newsData.urlToImage ?: "",
            contentDescription = "image view",
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
        )

        Text(
            text = newsData.source?.name ?: "",
            style = TextStyle(
                color = gray,
                fontSize = 10.sp
            ), modifier = Modifier.padding(8.dp)
        )
        Text(
            text = newsData.title ?: "",
            maxLines = 2,
            style = TextStyle(
                color = gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            ), modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
        )
        Text(
            text = newsData.publishedAt ?: "",
            style = TextStyle(
                color = gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            ), modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .align(Alignment.End)
        )

    }
}


