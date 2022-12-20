package com.belajar.aplikasicuaca.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.belajar.aplikasicuaca.screen.about.AboutScreen
import com.belajar.aplikasicuaca.screen.favorite.FavViewModel
import com.belajar.aplikasicuaca.screen.favorite.MainFavScreen
import com.belajar.aplikasicuaca.screen.main.MainScreen
import com.belajar.aplikasicuaca.screen.main.MainWeatherViewModel
import com.belajar.aplikasicuaca.screen.setting.SettingScreen
import com.belajar.aplikasicuaca.screen.setting.SettingViewModel
import com.belajar.aplikasicuaca.screen.splashScreen.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationAdapter() {
    val navController = rememberAnimatedNavController()
    val vm = hiltViewModel<MainWeatherViewModel>()
    val favVm = hiltViewModel<FavViewModel>()
    val setVm = hiltViewModel<SettingViewModel>()
    AnimatedNavHost(
        navController = navController,
        startDestination = Route.SplashScreen.route
    ) {

        composable(
            Route.Home.route + "/{city}",
            arguments = listOf(navArgument("city") {
                type = NavType.StringType
                defaultValue = "China"
            })
        ) {
            MainScreen(
                vm = vm,
                favVm = favVm,
                navController = navController,
                city = it.arguments!!.getString("city")!!,
                setVm = setVm
            )
        }

        composable(Route.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Route.Favorite.route) {
            MainFavScreen(navController = navController, favVm = favVm)
        }

        composable(Route.Setting.route) {
            SettingScreen(navController = navController, setVm = setVm)
        }

        composable(Route.About.route) {
            AboutScreen(navController = navController)
        }
    }
}