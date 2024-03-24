package com.example.newsapp.NewsApp.model

import androidx.compose.ui.graphics.Color
import com.example.newsapplication.R
import com.example.newsapplication.ui.theme.businessBackground
import com.example.newsapplication.ui.theme.environmentBackground
import com.example.newsapplication.ui.theme.healthBackground
import com.example.newsapplication.ui.theme.politicsBackground
import com.example.newsapplication.ui.theme.scienceBackground
import com.example.newsapplication.ui.theme.sportsBackground

data class Category(
    val categoryId: String? = null,
    val image: Int? = null,
    val categoryName: Int,
    val backgroundColor: Color? = null
)

val categoryList = listOf<Category>(
    Category(
        "sports",
        R.drawable.ball, R.string.sports, sportsBackground
    ),
    Category("entertainment", R.drawable.politics, R.string.entertainment, politicsBackground),
    Category("health", R.drawable.health, R.string.health, healthBackground),
    Category("business", R.drawable.bussines, R.string.business, businessBackground),
    Category("technology", R.drawable.environment, R.string.technology, environmentBackground),
    Category("science", R.drawable.science, R.string.science, scienceBackground)
)

