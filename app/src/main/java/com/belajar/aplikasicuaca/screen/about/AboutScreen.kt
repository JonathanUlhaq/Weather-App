package com.belajar.aplikasicuaca.screen.about

import android.hardware.lights.Light
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.belajar.aplikasicuaca.components.TopBar

@Composable
fun AboutScreen(
    navController:NavController
) {
    Scaffold(
        topBar = {
            TopBar(
                city = "About",
                isMainScreen = false,
                navController = navController
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(text = "This application is made by Jovian",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center)
                Text(text = "This application's weather data is from openweather and manages it using retrofit and room databases",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center)
            }
        }
    }
}