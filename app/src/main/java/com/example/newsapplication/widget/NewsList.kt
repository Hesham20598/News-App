package com.example.newsapplication.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.newsapplication.fragments.news.NewsViewModel
import com.example.newsapplication.helper.CustomLog
import com.example.newsapplication.model.News.NewsItem
import com.example.newsapplication.helper.ItemDetailsFragmentScreen


@Composable
fun NewsList(vm: NewsViewModel, list: List<NewsItem>, navController: NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list.size) { position ->

            NewsCard(list.get(position), navController, onNewsItemClick = {
                vm.itemTitle = list.get(position).title
                    ?: "null ya raye2"
                if (navController.currentDestination?.route != ItemDetailsFragmentScreen.ROUTE_NAME)
                    navController.navigate("${ItemDetailsFragmentScreen.ROUTE_NAME}")
                else {
                    CustomLog("we are there already ... ")
                }

            })

        }
    }
}