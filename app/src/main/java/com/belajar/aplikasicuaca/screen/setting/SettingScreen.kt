package com.belajar.aplikasicuaca.screen.setting

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.belajar.aplikasicuaca.components.TopBar
import com.belajar.aplikasicuaca.models.UnitModel.UnitModel

@Composable
fun SettingScreen(
    navController: NavController,
    setVm: SettingViewModel
) {

    val checked = remember {
        mutableStateOf(false)
    }

    val unit = remember {
        mutableStateOf("")
    }

    if (checked.value) unit.value = "metric" else unit.value = "imperial"

    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopBar(
                city = "Setting",
                isMainScreen = false,
                navController = navController
            )
        }
    ) {
        Surface(
            Modifier
                .fillMaxSize()
                .padding(it),
            color = Color.Transparent
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Change Suhu")
                Spacer(modifier = Modifier.height(12.dp))
                IconToggleButton(
                    checked = !checked.value,
                    onCheckedChange = {
                        checked.value = !checked.value
                    }
                ) {
                    Surface(
                        shape = RoundedCornerShape(22.dp),
                        elevation = 8.dp,
                    ) {
                        Text(
                            text = if (checked.value) "Celcius" else "Farenheit",
                            modifier = Modifier.padding(20.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {
                    setVm.deleteAllUnit()
                    setVm.insertUnit(UnitModel(unit = unit.value))
                    Toast.makeText(context,"Berhasil disimpan",Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }) {
                    Text(text = "Save")
                }
            }
        }
    }
}