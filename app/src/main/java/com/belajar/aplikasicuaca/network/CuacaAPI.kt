package com.belajar.aplikasicuaca.network

import com.belajar.aplikasicuaca.models.ModelCuaca.Cuaca
import com.belajar.aplikasicuaca.utils.Const
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CuacaAPI {
    @GET("data/2.5/forecast/daily")
    suspend fun getAllData(
        @Query("q")q:String,
        @Query("appid")appid:String = Const.API_KEY,
        @Query("units")units:String = "imperial"
    ):Cuaca
}