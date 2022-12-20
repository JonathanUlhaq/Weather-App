package com.belajar.aplikasicuaca.widgets.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.belajar.aplikasicuaca.models.ModelCuaca.Cuaca
import com.belajar.aplikasicuaca.utils.Formatter
import com.belajar.aplikasicuaca.utils.imageUrl

@Composable
fun MainHeader(
    cuaca:Cuaca
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(text = Formatter().dateFormatter(cuaca.list!![0].dt),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            shape = CircleShape,
            color = Color(0xFFFFD740),
            modifier = Modifier
                .size(180.dp)
        ) {
            Column(
                Modifier.padding(2.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(model = imageUrl(cuaca.list.first().weather.first().icon),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(60.dp))
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "${cuaca.list.first().temp.day}º")
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "${cuaca.list.first().weather.first().main}")
            }
        }
    }
}
