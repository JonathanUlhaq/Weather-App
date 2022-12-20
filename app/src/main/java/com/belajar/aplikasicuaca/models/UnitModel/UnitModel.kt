package com.belajar.aplikasicuaca.models.UnitModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_tb")
data class UnitModel(
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit: String
)
