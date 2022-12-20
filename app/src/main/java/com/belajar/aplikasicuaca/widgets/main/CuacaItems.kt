package com.belajar.aplikasicuaca.widgets.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.belajar.aplikasicuaca.components.CuacaItem
import com.belajar.aplikasicuaca.models.ModelCuaca.Cuaca
import com.belajar.aplikasicuaca.models.ModelCuaca.WeatherItem

@Composable
fun CuacaItems(cuaca: List<WeatherItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This Week",
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(content = {
          itemsIndexed(cuaca) {  index, item->
            CuacaItem(cuaca = item)
          }
        })
    }
    }
}