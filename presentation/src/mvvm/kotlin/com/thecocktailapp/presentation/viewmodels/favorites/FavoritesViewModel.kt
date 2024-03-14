package com.thecocktailapp.presentation.viewmodels.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecocktailapp.domain.usecases.common.GetFavoriteDrinks
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.ErrorVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteDrinks: @JvmSuppressWildcards GetFavoriteDrinks,
) : ViewModel() {

    private val _state = MutableStateFlow<FavoritesUiState>(value = FavoritesUiState.Idle)
    val state = _state.asStateFlow()

    fun onExecuteGetFavorites() {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                val list = getFavoriteDrinks().map { it.transform() }
                if (list.isEmpty()) {
                    _state.value = FavoritesUiState.Error(error = ErrorVO.FavoritesNotFound)
                } else {
                    _state.value = FavoritesUiState.Success(list = list)
                }
            }
        }
    }

    sealed class FavoritesUiState {
        object Idle : FavoritesUiState()
        data class Error(val error: ErrorVO) : FavoritesUiState()
        data class Success(val list: List<DrinkVO>) : FavoritesUiState()
    }

}