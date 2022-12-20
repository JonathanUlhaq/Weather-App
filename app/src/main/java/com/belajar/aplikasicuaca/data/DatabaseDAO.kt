package com.belajar.aplikasicuaca.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.belajar.aplikasicuaca.models.ModelFavorite.Favorite
import com.belajar.aplikasicuaca.models.UnitModel.UnitModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDAO {
    @Query("SELECT * FROM tb_favorite WHERE country =:country")
    fun checkCountry(country: String): Flow<List<Favorite>>

    @Query("SELECT * FROM tb_favorite")
    fun getAllFav(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(favorite: Favorite)

    @Query("DELETE FROM tb_favorite")
    suspend fun deleteAllFav()

    @Delete
    suspend fun deleteFav(fav: Favorite)

//    Unit
    @Query("DELETE FROM unit_tb")
    suspend fun deleteAllUnit()

    @Insert
    suspend fun insertUnit(unit:UnitModel)

    @Query("SELECT * FROM unit_tb")
    fun getAllUnit(): Flow<List<UnitModel>>

}
