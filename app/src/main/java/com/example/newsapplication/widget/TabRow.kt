package com.example.newsapplication.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.NewsApp.api.ApiManager
import com.example.newsapplication.fragments.news.NewsViewModel
import com.example.newsapplication.model.Source.SourcesItem
import com.example.newsapplication.model.Source.SourcesResponse
import com.example.newsapplication.ui.theme.green
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsSourcesTabRow(
    vm: NewsViewModel = viewModel(),
    categoryId: String,
    onTabSelected: (sourceId: String) -> Unit,
) {
    val selectedIndexState = remember {
        mutableIntStateOf(0)
    }
    val newsSources = remember { mutableStateListOf<SourcesItem?>() }
    LaunchedEffect(Unit) {

        ApiManager.getNewsServices()
            .getNewsSources(
                category = categoryId,
                language = "en"
            )
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    val sources = response.body()?.sources
                    if (sources?.isNotEmpty() == true) {
                        newsSources.addAll(sources)
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    ScrollableTabRow(
        selectedTabIndex = selectedIndexState.intValue,
        edgePadding = 8.dp,
        divider = {},
        indicator = {}) {
        newsSources.forEachIndexed { index, Item ->

            LaunchedEffect(Unit) {
                if (newsSources.isNotEmpty()) {
                    onTabSelected(newsSources.get(0)?.id ?: "")
                }
            }

            Tab(
                selected = selectedIndexState.intValue == index,
                onClick = {
                    onTabSelected(Item?.id ?: "")
                    selectedIndexState.intValue = index
                },
                selectedContentColor = Color.White,
                unselectedContentColor = green,
                modifier = Modifier.padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    text = Item?.name ?: "", style = TextStyle(
                        fontSize = 14.sp
                    ), modifier = if (selectedIndexState.intValue == index) {
                        Modifier
                            .background(green, RoundedCornerShape(50))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    } else {
                        Modifier
                            .border(
                                width = 2.dp,
                                color = green,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    }
                )
            }

        }


    }
}