package com.example.newsapplication.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapplication.R
import com.example.newsapplication.ui.theme.black
import com.example.newsapplication.ui.theme.green


@Composable
fun NewsDrawerSheet(onCategoriesClick: () -> Unit, onSettingsClick: () -> Unit) {
    ModalDrawerSheet(modifier = Modifier.fillMaxWidth(.7f)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.15f)
                .background(green)

        ) {
            Text(
                text = stringResource(R.string.news_app_2),
                style = TextStyle(
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
        DrawerSheetContentRow(
            id = R.drawable.ic_categories, description = "Categories",
            stringResource(R.string.categories),
            onItemClick = {
                onCategoriesClick()
            }
        )
        DrawerSheetContentRow(
            id = R.drawable.ic_settings, description = "Settings",
            stringResource(R.string.settings),
            onItemClick = {
                onSettingsClick()
            }
        )
    }
}

@Composable
fun DrawerSheetContentRow(id: Int, description: String, text: String, onItemClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 12.dp, start = 12.dp)
            .clickable {
                onItemClick()
            }
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = description,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text, style = TextStyle(
                fontSize = 24.sp,
                color = black,
                fontWeight = FontWeight.Bold
            )
        )

    }
}