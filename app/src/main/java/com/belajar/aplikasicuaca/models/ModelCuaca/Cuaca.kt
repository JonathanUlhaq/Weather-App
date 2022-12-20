package com.belajar.aplikasicuaca.models.ModelCuaca

data class Cuaca(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    val list: List<WeatherItem>? = null,
    val message: Double? = null
)