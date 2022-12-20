package com.belajar.aplikasicuaca.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajar.aplikasicuaca.models.UnitModel.UnitModel
import com.belajar.aplikasicuaca.repositories.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val repo: SettingRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<List<UnitModel>>(emptyList())
    val uiState = _uiState.asStateFlow()


    fun getAllUnit(empty: (Boolean) -> Unit) {
        viewModelScope.launch {
            repo.getAllUnit().distinctUntilChanged().collect { item ->
                if (item.isEmpty()) {
                    empty.invoke(false)
                } else {
                    empty.invoke(true)
                    _uiState.value = item
                }
            }
        }
    }

    fun insertUnit(unit:UnitModel) {
        viewModelScope.launch {
            repo.insertUnit(unit)
        }
    }


    fun deleteAllUnit() {
        viewModelScope.launch {
            repo.deleteAllUnit()
        }
    }
}