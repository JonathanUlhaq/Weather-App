package com.belajar.aplikasicuaca.models.ModelFavorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_favorite")
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "country")
    val country:String,

    @ColumnInfo(name = "country_id")
    val country_id:String
)
