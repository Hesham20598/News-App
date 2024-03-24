package com.example.newsapplication.fragments.noResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.newsapplication.R
import com.example.newsapplication.ui.theme.black
import com.example.newsapplication.ui.theme.green
import com.example.newsapplication.helper.CustomLog

@Composable
fun NoResultFragment() {
    CustomLog("we are in temp fragment now")
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(.4f),
            painter = painterResource(id = R.drawable.no_results),
            contentDescription = "there is no result here",
            colorFilter = ColorFilter.tint(green)
        )
        Text(
            text = stringResource(id = R.string.there_is_no_results_here_yet), style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = black,
                fontFamily = FontFamily.Cursive
            )
        )
    }
}