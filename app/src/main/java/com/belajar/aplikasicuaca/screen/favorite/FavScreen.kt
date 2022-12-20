package com.belajar.aplikasicuaca.screen.favorite

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.belajar.aplikasicuaca.components.TopBar
import com.belajar.aplikasicuaca.navigation.Route
import com.belajar.aplikasicuaca.widgets.favorite.FavoriteItems

@Composable
fun MainFavScreen(
    navController: NavController,
    favVm: FavViewModel
) {

    favVm.showAllFavorite()
    val uiState = favVm.uiState.collectAsState().value
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                city = "Favorite",
                isMainScreen = false,
                navController = navController
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(12.dp)
        ) {
            AnimatedVisibility(visible = uiState.isEmpty()) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(text = "Favorite mu masih kosong",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray)
                }
            }
            AnimatedVisibility(visible = uiState.isNotEmpty()) {
                FavoriteItems(list = uiState,
                    goDetail = { item ->
                        navController.navigate(Route.Home.route + "/${item.country}")
                    }) { item ->
                    favVm.deleteFavById(item)
                    Toast.makeText(context,"Berhasil dihapus",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}