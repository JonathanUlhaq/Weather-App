package com.belajar.aplikasicuaca.navigation

sealed class Route(val route: String) {
    object Home : Route("home")
    object Favorite : Route("fav")
    object Setting : Route("setting")
    object About : Route("about")
    object SplashScreen : Route("splash_screen")
}
