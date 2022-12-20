package com.belajar.aplikasicuaca.repositories

import com.belajar.aplikasicuaca.data.DatabaseDAO
import com.belajar.aplikasicuaca.models.UnitModel.UnitModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SettingRepository @Inject constructor(private val dao:DatabaseDAO) {
    fun getAllUnit():Flow<List<UnitModel>> = dao.getAllUnit().flowOn(Dispatchers.IO).conflate()
    suspend fun insertUnit(unitModel: UnitModel) = dao.insertUnit(unitModel)
    suspend fun deleteAllUnit() = dao.deleteAllUnit()
}