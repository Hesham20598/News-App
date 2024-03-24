package com.example.newsapp.NewsApp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://newsapi.org/v2/")
        .build()

    fun getNewsServices() : NewsServices{
        return retrofit.create(NewsServices::class.java)
    }
}