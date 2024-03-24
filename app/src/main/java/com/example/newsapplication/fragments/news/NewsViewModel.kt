package com.example.newsapplication.fragments.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsapp.NewsApp.api.ApiManager
import com.example.newsapplication.model.News.NewsItem
import com.example.newsapplication.model.News.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:ViewModel() {
    var itemTitle by mutableStateOf("")
    val newsStatesItem =  mutableStateListOf<NewsItem>()

    fun getNews(sourceId:String){
        ApiManager.getNewsServices()
            .getNewsBySource(sourceId = sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    newsStatesItem.clear()
                    val newsList = response.body()?.articles
                    if (newsList?.isNotEmpty() == true) {
                        newsStatesItem.addAll(newsList)
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}