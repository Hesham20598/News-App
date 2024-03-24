@file:Suppress("DEPRECATION")

package com.example.newsapplication.fragments.settings

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapplication.Activities.home.HomeScreen
import com.example.newsapplication.R
import com.example.newsapplication.helper.CustomLog
import com.example.newsapplication.ui.theme.black
import com.example.newsapplication.ui.theme.green
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsFragmentContent(vm: SettingsViewModel = viewModel()) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(29.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.language), style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = black
            ), modifier = Modifier
                .padding(bottom = 23.dp)
                .align(Alignment.Start)
        )

        ExposedDropdownMenuBox(
            expanded = vm.isExpanded,
            onExpandedChange = { vm.isExpanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = vm.selectedLanguage, onValueChange = {

                }, readOnly = true,

                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = vm.isExpanded
                    )
                }, colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = green,
                    unfocusedBorderColor = Color.Black,
                    focusedTextColor = green,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ), modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = vm.isExpanded,
                onDismissRequest = { vm.isExpanded = false }) {

                DropdownMenuItem(text = {
                    Text(text = stringResource(id = R.string.english))
                }, onClick = {
                    vm.selectedLanguage = "English"
                    vm.isExpanded = false
                    vm.position = 0
                    CustomLog("you clicked on english")


                }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)

                DropdownMenuItem(text = {
                    Text(text = stringResource(id = R.string.arabic))
                }, onClick = {
                    CustomLog("you clicked on arabic")
                    vm.selectedLanguage = "العربية"
                    vm.isExpanded = false
                    vm.position = 1


                }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)

            }

        }
        if (vm.position != null) {
            SetLanguage(position = vm.position)
        }

    }
}

@SuppressLint("ObsoleteSdkInt")
@Composable
fun SetLanguage(position: Int?) {
    val activity = LocalContext.current as HomeScreen
    val locale = Locale(
        when (position) {
            0 -> "en"
            1 -> "ar"
            else -> "ar"
        }
    )
    Locale.setDefault(locale)
    val configuration = LocalConfiguration.current
    configuration.setLocale(locale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        configuration.setLocale(locale)

    configuration.setLocale(locale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        configuration.setLocale(locale)
    else
        configuration.locale = locale
    var resources = LocalContext.current.resources
    resources.updateConfiguration(configuration, resources.displayMetrics)
    activity.finish()
    activity.startActivity(activity.intent)
}




