package com.example.newsapplication.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapplication.Activities.home.HomeViewModel
import com.example.newsapplication.R
import com.example.newsapplication.ui.theme.green


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(
    vm: HomeViewModel = viewModel(),
    showSearchIcon: Boolean,
    showMenuIcon: Boolean,
    onToolbarSearchIconClick: () -> Unit,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)),
        title = {
            Text(
                text = if (vm.appBarTitle.equals("")) {
                    stringResource(R.string.news_app)
                } else {
                    vm.appBarTitle
                },
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Color.White, textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

        }, actions = {
            if (showSearchIcon == true) {

                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(35.dp)
                        .clickable {
                            onToolbarSearchIconClick()
                        }

                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = green),
        navigationIcon = {
            if (showMenuIcon) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Icon side menu",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(35.dp)
                        .clickable {
                            onNavigationIconClick()
                        }
                )
            }
        }

    )
}

@Preview
@Composable
fun NewsAppBarPreview() {
    NewsAppBar(showMenuIcon = true, showSearchIcon = true, onToolbarSearchIconClick = {}) {

    }
}