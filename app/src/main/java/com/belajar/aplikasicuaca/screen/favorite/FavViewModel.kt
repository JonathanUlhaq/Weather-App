package com.belajar.aplikasicuaca.screen.favorite

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajar.aplikasicuaca.models.ModelFavorite.Favorite
import com.belajar.aplikasicuaca.repositories.FavRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(private val repository: FavRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<List<Favorite>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun insertFav(fav: Favorite) {
        viewModelScope.launch {
            repository.insertFav(fav)
        }
    }

    init {
        showAllFavorite()
    }

    fun showAllFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFav().distinctUntilChanged().collect{ item ->
                if (!item.isEmpty()) _uiState.value = item
                else _uiState.value = emptyList()
            }
        }
    }

    fun deleteFavById(fav:Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFav(fav)
        }
    }

    fun deleteAllFav() {
        viewModelScope.launch {
            repository.deleteAllFav()
        }
    }

    fun checkFav(country: String, tersimpam: MutableState<Boolean>) {
        viewModelScope.launch {
            repository.checkFav(country).collect { item ->
                tersimpam.value = !item.isEmpty()
            }
        }
    }
}