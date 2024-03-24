package com.example.newsapplication.Activities.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsapp.NewsApp.api.ApiManager
import com.example.newsapplication.model.News.NewsItem
import com.example.newsapplication.model.News.NewsResponse
import com.example.newsapplication.helper.CustomLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    var isIconSearchShouldBeShown by mutableStateOf(false)
    var isSearchBarShouldBeShown by mutableStateOf(false)
    var searchBarShow by mutableStateOf(true)
    var appBarTitle by mutableStateOf("")
    var newsItem by mutableStateOf<NewsItem?>(null)
    var valueForSearchBar by mutableStateOf("")


    var newsList by mutableStateOf(listOf<NewsItem>())
    var editTextSearchValue by mutableStateOf("")
    var startSearching by mutableStateOf(false)

    fun getItemDetails(title: String) {
        ApiManager.getNewsServices().getArticleDetails(title = title, searchIn = "title")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        val articles = response.body()?.articles
                        if (articles?.isNotEmpty() == true) {
                            newsItem = articles[0]
                            CustomLog("api call is done successfully ....")
                        }
                    } else CustomLog("Oops something went wrong ...")
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    CustomLog("unfortunately this is a failure message ...  -> ${t.message} ")
                }
            })
    }

    fun createApiCall(query: String) {
        ApiManager.getNewsServices().getSearchedNews(searchValue = query)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        val articlesResponse = response.body()?.articles
                        if (articlesResponse?.isNotEmpty() == true) {
                            newsList = articlesResponse
                            CustomLog("we now have the api list")
                        } else CustomLog("the list which is come from api is empty")

                    } else CustomLog("something went wrong")
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                    CustomLog("this a failure exception and the message is -> ${t.message}")
                }

            })

    }


}