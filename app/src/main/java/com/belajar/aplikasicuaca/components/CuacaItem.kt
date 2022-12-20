package com.belajar.aplikasicuaca.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.belajar.aplikasicuaca.models.ModelCuaca.WeatherItem
import com.belajar.aplikasicuaca.utils.Formatter
import com.belajar.aplikasicuaca.utils.imageUrl

@Composable
fun CuacaItem(
    cuaca:WeatherItem
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp, bottomStart = 20.dp),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = Formatter().dayFormat(cuaca.dt),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)

            AsyncImage(model = imageUrl(cuaca.weather[0].icon),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(30.dp))

            Surface(
                shape = CircleShape,
                color = Color(0xFFFFD740)
            ) {
                Text(text = cuaca.weather[0].description,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(4.dp))
            }

            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                ) {
                    append(cuaca.temp.max.toString()+"º")
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray,
                        fontSize = 10.sp
                    )
                    ) {
                        append(cuaca.temp.min.toString()+"º")
                    }
                }
            })
        }
    }

}