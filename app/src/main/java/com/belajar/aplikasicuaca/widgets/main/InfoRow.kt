package com.belajar.aplikasicuaca.widgets.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belajar.aplikasicuaca.R
import com.belajar.aplikasicuaca.models.ModelCuaca.Cuaca

@Composable
fun InfoRow(
    cuaca: Cuaca
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.humidity) ,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp
                    ))
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "${cuaca.list!!.first().humidity}%",
                fontSize = 12.sp)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.pressure) ,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp
                    ) )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "${cuaca.list!!.first().pressure}psi",
                fontSize = 12.sp)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.wind) ,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp) )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "${cuaca.list!!.first().speed}m/s",
                fontSize = 12.sp)
        }
    }
}