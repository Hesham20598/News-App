package com.example.newsapplication.Activities.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapplication.R
import com.example.newsapplication.fragments.categories.CategoriesFragment
import com.example.newsapplication.fragments.itemDetails.ItemDetailsFragment
import com.example.newsapplication.fragments.news.NewsFragment
import com.example.newsapplication.fragments.news.NewsViewModel
import com.example.newsapplication.fragments.noResult.NoResultFragment
import com.example.newsapplication.fragments.search.SearchFragment
import com.example.newsapplication.fragments.settings.SettingsFragmentContent
import com.example.newsapplication.helper.CategoriesFragmentScreen
import com.example.newsapplication.helper.CustomLog
import com.example.newsapplication.helper.ItemDetailsFragmentScreen
import com.example.newsapplication.helper.NewsFragmentScreen
import com.example.newsapplication.helper.NoResultFragmentScreen
import com.example.newsapplication.helper.SearchFragmentScreen
import com.example.newsapplication.helper.SettingsFragmentScreen
import com.example.newsapplication.ui.theme.NewsApplicationTheme
import com.example.newsapplication.widget.CustomSearchBar
import com.example.newsapplication.widget.NewsAppBar
import com.example.newsapplication.widget.NewsDrawerSheet
import kotlinx.coroutines.launch

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: HomeViewModel = viewModel()
            val newsViewModel: NewsViewModel = viewModel()
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            NewsApplicationTheme {

                ModalNavigationDrawer(
                    drawerContent = {
                        NewsDrawerSheet(
                            onCategoriesClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                                navController.popBackStack()
                                if (navController.currentDestination?.route != CategoriesFragmentScreen.ROUTE_NAME) {
                                    navController.navigate(CategoriesFragmentScreen.ROUTE_NAME)
                                }

                            }, onSettingsClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                                navController.popBackStack()
                                if (navController.currentDestination?.route != SettingsFragmentScreen.ROUTE_NAME) {
                                    navController.navigate(SettingsFragmentScreen.ROUTE_NAME)
                                }
                            })


                    }, drawerState = drawerState
                ) {
                    Scaffold(
                        topBar = {
                            if (vm.isSearchBarShouldBeShown) {
                                CustomSearchBar(vm)

                            } else {
                                NewsAppBar(
                                    showSearchIcon = vm.isIconSearchShouldBeShown,
                                    showMenuIcon = true,
                                    onToolbarSearchIconClick = {
                                        vm.isSearchBarShouldBeShown = true
                                        vm.searchBarShow = true
                                        if (navController.currentDestination?.route != SearchFragmentScreen.ROUTE_NAME) {
                                            navController.navigate(SearchFragmentScreen.ROUTE_NAME)
                                            CustomLog("we are going to SearchFragment")
                                        } else {
                                            CustomLog("we are there already...")
                                        }
                                    }, onNavigationIconClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                )
                            }
                        }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = CategoriesFragmentScreen.ROUTE_NAME,
                            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
                        ) {
                            composable(CategoriesFragmentScreen.ROUTE_NAME) {
                                vm.isIconSearchShouldBeShown = false
                                vm.isSearchBarShouldBeShown = false
                                vm.appBarTitle = stringResource(id = R.string.news_app)
                                CategoriesFragment(navHostController = navController)
                            }
                            composable("${NewsFragmentScreen.ROUTE_NAME}/{category_id}/{category_name}",
                                arguments = listOf(
                                    navArgument("category_id") {
                                        type = NavType.StringType
                                    }, navArgument("category_name") {
                                        type = NavType.IntType
                                    }
                                )) { navBackStackEntry ->
                                val categoryId =
                                    navBackStackEntry.arguments?.getString("category_id")
                                val categoryName =
                                    navBackStackEntry.arguments?.getInt("category_name")
//
                                NewsFragment(
                                    navController = navController,
                                    categoryId = categoryId ?: "",
                                    vm = newsViewModel
                                )
                                vm.newsItem = null
                                vm.appBarTitle = stringResource(id = categoryName!!)
                                vm.isIconSearchShouldBeShown = true
                                vm.isSearchBarShouldBeShown = false


                            }
                            composable(SettingsFragmentScreen.ROUTE_NAME) {
                                vm.appBarTitle = stringResource(id = R.string.settings)
                                SettingsFragmentContent()
                            }
                            composable("${SearchFragmentScreen.ROUTE_NAME}")

                            { navBackStackEntry ->
//                                val value = navBackStackEntry.arguments?.getString("value_search")
                                vm.appBarTitle = stringResource(id = R.string.search_here)
                                vm.newsItem = null
                                if (vm.searchBarShow) {
                                    vm.isSearchBarShouldBeShown = true
                                } else vm.isSearchBarShouldBeShown = false
                                SearchFragment(
                                    navController,
                                    vm = vm,
                                    newsViewModel
                                )
                            }
                            composable(NoResultFragmentScreen.ROUTE_NAME) {
                                vm.appBarTitle = stringResource(id = R.string.search_here)
                                if (vm.searchBarShow)
                                    vm.isSearchBarShouldBeShown = true
                                else vm.isSearchBarShouldBeShown = false
                                NoResultFragment()
                            }

                            composable(ItemDetailsFragmentScreen.ROUTE_NAME) {

                                ItemDetailsFragment(vm, newsViewModel, navController)
                            }
                        }
                    }


                }


            }

        }
    }

    fun SeeAllArticle(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}