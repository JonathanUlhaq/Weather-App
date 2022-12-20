package com.belajar.aplikasicuaca.di

import android.content.Context
import androidx.room.Room
import com.belajar.aplikasicuaca.data.DatabaseDAO
import com.belajar.aplikasicuaca.data.FavoriteDatabase
import com.belajar.aplikasicuaca.network.CuacaAPI
import com.belajar.aplikasicuaca.utils.Const
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
class Module {

    @Provides
    @Singleton
    fun provideDAO(database:FavoriteDatabase):DatabaseDAO = database.datbaseDAO()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context):FavoriteDatabase {
        return Room.databaseBuilder(
            context,
           FavoriteDatabase::class.java,
            "db_fav"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttp():OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level =HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(clinet:OkHttpClient):CuacaAPI {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clinet)
            .build()
            .create(CuacaAPI::class.java)
    }

}