package com.belajar.aplikasicuaca.models.ModelCuaca

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)