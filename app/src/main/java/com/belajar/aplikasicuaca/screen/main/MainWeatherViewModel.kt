package com.belajar.aplikasicuaca.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajar.aplikasicuaca.models.ModelCuaca.Cuaca
import com.belajar.aplikasicuaca.models.ModelCuaca.Weather
import com.belajar.aplikasicuaca.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainWeatherViewModel @Inject constructor(private val repo: WeatherRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<Cuaca> = MutableStateFlow(Cuaca())
    val uiState = _uiState.asStateFlow()

    fun getAllWeather(city: String,unit:String, error: (String) -> Unit) {
        viewModelScope.launch {

            try {
                _uiState.value = repo.getAllData(city,unit)
            } catch (e: Exception) {
                error.invoke(e.localizedMessage)
            }
        }
    }

}