package com.example.newsapplication.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapplication.Activities.home.HomeScreen
import com.example.newsapplication.R
import com.example.newsapplication.model.News.NewsItem
import com.example.newsapplication.ui.theme.black
import com.example.newsapplication.ui.theme.gray
import com.example.newsapplication.ui.theme.lightBlack


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemDetailsCard(newsData: NewsItem, navController: NavHostController) {
    val context = (LocalContext.current) as HomeScreen
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
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
                .padding(start = 8.dp, end = 16.dp, bottom = 16.dp)
                .align(Alignment.End)
        )

        Text(
            text = newsData.description ?: "",
            style = TextStyle(
                color = gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            ), modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)


        )
        TextButton(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End),
            onClick = {
                context.SeeAllArticle(newsData.url ?: "")
            }) {

            Text(
                text = stringResource(R.string.view_full_article), style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = lightBlack
                ), modifier = Modifier.align(Alignment.CenterVertically)
            )
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "move to news screen website",
                tint = black,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
            )
        }

    }

}




