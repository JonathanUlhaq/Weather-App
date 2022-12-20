package com.belajar.aplikasicuaca.repositories

import com.belajar.aplikasicuaca.models.ModelCuaca.Cuaca
import com.belajar.aplikasicuaca.network.CuacaAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:CuacaAPI) {
    suspend fun getAllData(city:String,unit:String):Cuaca = api.getAllData(q = city, units = unit)
}