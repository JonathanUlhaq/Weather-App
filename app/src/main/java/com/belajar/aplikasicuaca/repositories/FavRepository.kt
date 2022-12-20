package com.belajar.aplikasicuaca.repositories

import com.belajar.aplikasicuaca.data.DatabaseDAO
import com.belajar.aplikasicuaca.models.ModelFavorite.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class FavRepository @Inject constructor(private val dao: DatabaseDAO) {

    fun checkFav(country:String):Flow<List<Favorite>> = dao.checkCountry(country).flowOn(Dispatchers.IO).conflate()
    fun getAllFav():Flow<List<Favorite>> = dao.getAllFav().flowOn(Dispatchers.IO).conflate()
    suspend fun insertFav(fav:Favorite) = dao.insertFav(fav)
    suspend fun deleteAllFav() = dao.deleteAllFav()
    suspend fun deleteFav(fav: Favorite) = dao.deleteFav(fav)
}