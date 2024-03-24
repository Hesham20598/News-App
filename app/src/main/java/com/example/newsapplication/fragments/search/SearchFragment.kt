package com.example.newsapplication.fragments.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.newsapplication.Activities.home.HomeViewModel
import com.example.newsapplication.fragments.news.NewsViewModel
import com.example.newsapplication.fragments.noResult.NoResultFragment
import com.example.newsapplication.helper.ItemDetailsFragmentScreen
import com.example.newsapplication.helper.CustomLog
import com.example.newsapplication.widget.NewsCard

@Composable
fun SearchFragment(
    navController: NavHostController,
    vm: HomeViewModel ,
    newsViewModel: NewsViewModel
) {


    if (vm.newsList.isEmpty()) {
        NoResultFragment()
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(vm.newsList.size) { position ->
                NewsCard(newsData = vm.newsList.get(position), navController = navController) {
                    newsViewModel.itemTitle = vm.newsList.get(position).title ?: "null ya raye2"
                    if (navController.currentDestination?.route != ItemDetailsFragmentScreen.ROUTE_NAME)
                        navController.navigate(ItemDetailsFragmentScreen.ROUTE_NAME)
                    else {
                        CustomLog("we are there already ... ")
                    }
                }

            }
        }
    }

}
