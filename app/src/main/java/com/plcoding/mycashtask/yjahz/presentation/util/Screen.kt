package com.plcoding.mycashtask.yjahz.presentation.util

sealed class Screen(val route: String) {
    data object AuthScreen: Screen("auth_screen")
    data object HomeScreen: Screen("home_screen")
    data object SplashScreen: Screen("splash_screen")
}
