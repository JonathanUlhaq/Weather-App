package com.belajar.aplikasicuaca.screen.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.belajar.aplikasicuaca.components.TopBar
import com.belajar.aplikasicuaca.models.ModelCuaca.Cuaca
import com.belajar.aplikasicuaca.models.ModelFavorite.Favorite
import com.belajar.aplikasicuaca.screen.favorite.FavViewModel
import com.belajar.aplikasicuaca.screen.setting.SettingViewModel
import com.belajar.aplikasicuaca.widgets.main.CuacaItems
import com.belajar.aplikasicuaca.widgets.main.InfoRow
import com.belajar.aplikasicuaca.widgets.main.MainHeader
import com.belajar.aplikasicuaca.widgets.main.SinerRIse

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    vm: MainWeatherViewModel,
    favVm: FavViewModel,
    city: String,
    navController: NavController,
    setVm: SettingViewModel
) {

    var citySearch by remember {
        mutableStateOf(city)
    }

    val isNotEmpty = remember {
        mutableStateOf(false)
    }

    val unit = remember {
        mutableStateOf("")
    }

    val search = remember {
        mutableStateOf("")
    }

    val searchValue = remember {
        mutableStateOf("china")
    }

    val tersimpan = remember {
        mutableStateOf(true)
    }

    if (citySearch.isNotEmpty()) {
        searchValue.value = citySearch
    }

    val context = LocalContext.current

    setVm.getAllUnit {
        isNotEmpty.value = it
    }
    val unitState = setVm.uiState.collectAsState().value
    if (isNotEmpty.value) {
        unit.value = unitState[0].unit
    } else {
        unit.value = "metric"
    }

    val uiState = vm.uiState.collectAsState().value
    vm.getAllWeather(searchValue.value, unit = unit.value) {
        when (it) {
            "HTTP 404 Not Found" -> {
                Toast.makeText(context, "Negara tidak ada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    uiState.city?.let { favVm.checkFav(it.name, tersimpan) }

    Log.d("tersimoan value: ", tersimpan.value.toString())
    val keyboardController = LocalSoftwareKeyboardController.current

    MainScaffold(
        cuaca = uiState, search = search,
        tersimpan = tersimpan,
        onSave = {
            favVm.insertFav(
                Favorite(
                    country = uiState.city!!.name,
                    country_id = uiState.city.country
                )
            )
            Toast.makeText(context, "Berhasil ditambahkan ke Favorite", Toast.LENGTH_SHORT).show()
        },
        navController = navController,
        deleteText = {
            search.value = ""
        }
    ) {

        searchValue.value = search.value.filter { !it.isWhitespace() }
        citySearch = ""
        keyboardController?.hide()


    }

}

@Composable
fun MainScaffold(
    cuaca: Cuaca,
    search: MutableState<String>,
    tersimpan: MutableState<Boolean>,
    onSave: () -> Unit,
    navController: NavController,
    deleteText: () -> Unit,
    onAction: () -> Unit
) {


    Scaffold(
        topBar = {

            TopBar(
                city = if (cuaca.city == null) "" else cuaca.city.name,
                country = if (cuaca.city == null) "" else cuaca.city.country,
                isMainScreen = true,
                value = search,
                tersimpan = tersimpan,
                onSave = {
                    onSave.invoke()
                },
                navController = navController,
                deleteText = {
                    deleteText.invoke()
                }
            ) {
                onAction.invoke()
            }


        }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(it)
        ) {

            if (cuaca.city == null) {
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            } else {
                MainHeader(cuaca = cuaca)
                Spacer(modifier = Modifier.height(14.dp))
                InfoRow(cuaca = cuaca)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                SinerRIse(cuaca = cuaca)
                CuacaItems(cuaca = cuaca.list!!)

            }
        }

    }
}

