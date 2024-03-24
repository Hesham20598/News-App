package com.example.newsapplication.fragments.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.NewsApp.model.categoryList
import com.example.newsapplication.R
import com.example.newsapplication.ui.theme.black
import com.example.newsapplication.widget.CategoryCard


@Composable
fun CategoriesFragment(navHostController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(18.dp)
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.pattern), contentScale = ContentScale.Crop)
    ) {
        Text(
            text = "${stringResource(id = R.string.pick_your_category)}\n" +
                    "${stringResource(id = R.string.of_interest)}",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = black
            ), modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.Start)
        )

        CategoriesGrid(navHostController = navHostController)

    }


}
@Composable
fun CategoriesGrid(navHostController: NavHostController) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categoryList.size) { position ->
            CategoryCard(
                categoryList.get(position), shape = if (position % 2 != 0) {
                    RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomEnd = 30.dp,
                        bottomStart = 0.dp
                    )
                } else {
                    RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 30.dp
                    )
                }, navController = navHostController
            )
        }
    }
}
