package com.example.newsapplication.fragments.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.newsapplication.R
import com.example.newsapplication.widget.NewsList
import com.example.newsapplication.widget.NewsSourcesTabRow

@Composable
fun NewsFragment(
    modifier: Modifier = Modifier,
    vm: NewsViewModel = viewModel(),
    navController: NavHostController,
    categoryId: String,
) {

    Column(
        modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.pattern), contentScale = ContentScale.Crop)
    ) {

        NewsSourcesTabRow(vm, categoryId) { sourceId ->

            vm.getNews(sourceId)
        }
        NewsList(vm, vm.newsStatesItem.toList(), navController = navController)
    }
}