package com.example.newsapplication.Activities.splash

import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {


    var onNavigation: OnNavigation? = null
    fun navigateToHomeScreen() {
        if (onNavigation != null) {
            onNavigation?.navigateToHomeScreen()
        }
    }

}


fun interface OnNavigation {
    fun navigateToHomeScreen()
}