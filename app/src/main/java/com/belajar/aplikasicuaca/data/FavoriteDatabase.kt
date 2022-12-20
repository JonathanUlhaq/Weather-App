package com.belajar.aplikasicuaca.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.belajar.aplikasicuaca.models.ModelFavorite.Favorite
import com.belajar.aplikasicuaca.models.UnitModel.UnitModel

@Database(entities = [Favorite::class,UnitModel::class], version = 2, exportSchema = false)
abstract class FavoriteDatabase:RoomDatabase() {
    abstract fun datbaseDAO():DatabaseDAO
}