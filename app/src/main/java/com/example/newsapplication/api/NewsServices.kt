package com.example.newsapp.NewsApp.api


import com.example.newsapp.NewsApp.model.Constants
import com.example.newsapplication.model.News.NewsResponse
import com.example.newsapplication.model.Source.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("category") category: String,
        @Query("language") language: String = "en"
    ): Call<SourcesResponse>


    @GET("everything")
    fun getNewsBySource(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("sources") sourceId: String,
        @Query("language") language: String = "en"
    ): Call<NewsResponse>

    @GET("everything")
    fun getSearchedNews(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("q") searchValue: String,
    ): Call<NewsResponse>

    @GET("everything")
    fun getArticleDetails(
        @Query("q") title: String,
        @Query("searchIn") searchIn: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ):Call<NewsResponse>
}