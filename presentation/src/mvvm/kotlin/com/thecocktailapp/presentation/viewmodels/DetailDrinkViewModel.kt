package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzs.core.domain.bo.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.GetDrinkById
import com.thecocktailapp.domain.usecases.detail.GetDrinkByIdUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.IsFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.IsFavoriteDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrinkUseCaseImpl
import com.thecocktailapp.presentation.utils.navigation.NavArg
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
class DetailDrinkViewModel @Inject constructor(
    private val addFavoriteDrink: @JvmSuppressWildcards AddFavoriteDrink,
    private val getDrinkById: @JvmSuppressWildcards GetDrinkById,
    private val isFavoriteDrink: @JvmSuppressWildcards IsFavoriteDrink,
    private val removeFavoriteDrink: @JvmSuppressWildcards RemoveFavoriteDrink,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private lateinit var drink: DrinkVO
    private val id = savedStateHandle.get<String>(key = NavArg.DrinkId.key) ?: "0"

    private val _isFavorite = MutableStateFlow(value = false)
    val isFavorite = _isFavorite.asStateFlow()

    private val _initialFavoriteValue = MutableStateFlow(value = false)
    val initialFavoriteValue = _initialFavoriteValue.asStateFlow()

    private val _state = MutableStateFlow<DetailDrinkUiState>(value = DetailDrinkUiState.Idle)
    val state = _state.asStateFlow()

    init {
        onExecuteGetDrinkById()
    }

    fun onExecuteGetDrinkById() {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                getDrinkById
                    .invoke(params = GetDrinkByIdUseCaseImpl.Params(id = id.toInt()))
                    .collect(::handleDrinkByIdResponse)
            }
        }
    }

    private suspend fun handleDrinkByIdResponse(result: Result<CocktailBO>) =
        withContext(Dispatchers.Main) {
            when (result) {
                is Result.Loading -> {
                    _state.value = DetailDrinkUiState.Loading
                }

                is Result.Response.Error<*> -> {
                    _state.value =
                        DetailDrinkUiState.Error(error = (result.code as ErrorBO).transform())
                }

                is Result.Response.Success -> {
                    drink = result.data.drinks.first().transform()
                    onExecuteIsFavoriteDrink()
                }
            }
        }

    private fun onExecuteIsFavoriteDrink() {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                isFavoriteDrink.invoke(IsFavoriteDrinkUseCaseImpl.Params(drinkId = id.toInt()))
                    .apply {
                        _isFavorite.value = this
                        _initialFavoriteValue.value = this
                    }
                _state.value = DetailDrinkUiState.Success(drink = drink)
            }
        }
    }

    fun addFavoriteDrink(drink: DrinkVO) {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                addFavoriteDrink.invoke(params = AddFavoriteDrinkUseCaseImpl.Params(drink = drink.transform()))
                _isFavorite.value = true
            }
        }
    }

    fun removeFavoriteDrink(drink: DrinkVO) {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                removeFavoriteDrink.invoke(params = RemoveFavoriteDrinkUseCaseImpl.Params(drink = drink.transform()))
                _isFavorite.value = false
            }
        }
    }

    sealed class DetailDrinkUiState {
        object Idle : DetailDrinkUiState()
        data class Error(val error: ErrorVO) : DetailDrinkUiState()
        object Loading : DetailDrinkUiState()
        data class Success(val drink: DrinkVO) : DetailDrinkUiState()
    }

}