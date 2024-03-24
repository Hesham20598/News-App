package com.example.newsapplication.fragments.itemDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.newsapplication.Activities.home.HomeViewModel
import com.example.newsapplication.fragments.news.NewsViewModel
import com.example.newsapplication.fragments.noResult.NoResultFragment
import com.example.newsapplication.helper.CustomLog
import com.example.newsapplication.widget.ItemDetailsCard

@Composable
fun ItemDetailsFragment(
    vm: HomeViewModel,
    newsViewModel : NewsViewModel,
    navHostController: NavHostController
) {
    CustomLog("we are in item details fragment")
    if (vm.newsItem == null) {
        LaunchedEffect(Unit) {
            vm.getItemDetails(newsViewModel.itemTitle)
            CustomLog("we made the api call")
        }
    }

    if (vm.newsItem != null) {
        CustomLog("we have an item here......")
        ItemDetailsCard(newsData = vm.newsItem!!, navController = navHostController)
    } else {
        CustomLog("the item is still null ya raye2")
        NoResultFragment()
    }
}
