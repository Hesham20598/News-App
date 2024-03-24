package com.example.newsapplication.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapplication.Activities.home.HomeViewModel
import com.example.newsapplication.R
import com.example.newsapplication.helper.CustomLog
import com.example.newsapplication.ui.theme.green

@Composable
fun CustomSearchBar(
    vm: HomeViewModel = viewModel(),

    ) {
    val focusManager = LocalFocusManager.current
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(green)

    ) {
        OutlinedTextField(
            value = vm.valueForSearchBar,
            modifier = Modifier
                .fillMaxWidth(.9f)
                .fillMaxHeight(.7f),
            onValueChange = {
                vm.valueForSearchBar = it
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = green
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search

            ),
            keyboardActions = KeyboardActions(onSearch = {
                if (vm.valueForSearchBar == "") CustomLog("empty value")
                else {
                    CustomLog("search query is ${vm.valueForSearchBar}")
                    vm.startSearching = true
                    vm.editTextSearchValue = vm.valueForSearchBar
                    vm.createApiCall(vm.valueForSearchBar)
                }
                focusManager.clearFocus()
            }),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White
            ),
            shape = CircleShape,
            singleLine = true,
            // Close Icon
            leadingIcon = {
                EditTextIcon(
                    icon = Icons.Default.Close,
                    description = "Close Icon",
                    leading = true
                ) {
                    if (vm.valueForSearchBar == "") {
                        vm.isSearchBarShouldBeShown = false
                        vm.searchBarShow = false
                    } else {
                        vm.valueForSearchBar = ""
                    }
                }
            },

            // Search Icon
            trailingIcon = {
                EditTextIcon(
                    icon = Icons.Default.Search,
                    description = "Search Icon",
                    leading = false
                ) {

                    if (vm.valueForSearchBar == "") CustomLog("empty value")
                    else {
                        CustomLog("search query is ${vm.valueForSearchBar}")
                        vm.startSearching = true
                        vm.editTextSearchValue = vm.valueForSearchBar
                        vm.createApiCall(vm.valueForSearchBar)
                    }
                }
            },
            placeholder = {
                Text(text = stringResource(R.string.search_for_articles), color = green, fontSize = 14.sp)
            },


            )
    }


}

@Composable
fun EditTextIcon(
    icon: ImageVector,
    description: String,
    leading: Boolean,
    onIconClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = description,
        tint = green,
        modifier = Modifier
            .padding(
                start =
                if (leading) {
                    16.dp
                } else 8.dp,
                end = if (leading) {
                    8.dp
                } else 16.dp
            )
            .size(30.dp)
            .clickable {
                onIconClick()
            }
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchBarPreview() {
    CustomSearchBar(
        vm = HomeViewModel()
    )
}
