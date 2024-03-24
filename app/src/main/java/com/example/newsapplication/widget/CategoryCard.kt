package com.example.newsapplication.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.NewsApp.model.Category
import com.example.newsapplication.helper.NewsFragmentScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    categoryItem: Category,
    shape: RoundedCornerShape,
    navController: NavHostController
) {

    Card(
        modifier = Modifier
            .padding(top = 20.dp, end = 10.dp, start = 10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(categoryItem.backgroundColor ?: Color.White),
        shape = shape,
        onClick = {
            navController.navigate("${NewsFragmentScreen.ROUTE_NAME}/${categoryItem.categoryId}/${categoryItem.categoryName}")
        }
    ) {

        Image(
            painter = painterResource(id = categoryItem.image ?: 0),
            contentDescription = "category item",
            modifier = Modifier
                .padding(25.dp)
                .height(100.dp)
                .align(Alignment.CenterHorizontally), contentScale = ContentScale.Crop

        )
        Text(
            text = stringResource(id = categoryItem.categoryName), style = TextStyle(
                fontSize = 22.sp,
                color = Color.White
            ),
            modifier = Modifier
                .padding(bottom = 18.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}