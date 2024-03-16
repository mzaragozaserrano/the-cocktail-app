package com.thecocktailapp.presentation.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.common.GetFavoriteDrinks
import com.thecocktailapp.domain.usecases.home.GetDrinksByType
import com.thecocktailapp.domain.usecases.home.GetDrinksByTypeUseCaseImpl
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.DrinkType
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
class HomeViewModel @Inject constructor(
    private val getDrinksByType: @JvmSuppressWildcards GetDrinksByType,
    private val getFavoriteDrinks: @JvmSuppressWildcards GetFavoriteDrinks,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(value = HomeUiState.Idle)
    val state = _state.asStateFlow()

    private var drinkType: DrinkType = DrinkType.Alcoholic

    private var list: MutableList<DrinkVO> = mutableListOf()

    init {
        onExecuteGetDrinksByType()
    }

    fun onExecuteGetDrinksByType() {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                getDrinksByType
                    .invoke(params = GetDrinksByTypeUseCaseImpl.Params(dbId = drinkType.dbId))
                    .collect(::handleDrinkByTypeResponse)
            }
        }
    }

    private suspend fun handleDrinkByTypeResponse(result: Result<CocktailBO>) =
        withContext(Dispatchers.Main) {
            when (result) {
                is Result.Loading -> {
                    _state.value = HomeUiState.Loading
                }

                is Result.Response.Error<*> -> {
                    _state.value =
                        HomeUiState.Error(error = (result.code as ErrorBO).transform())
                }

                is Result.Response.Success -> {
                    list.clear()
                    list.addAll(result.data.drinks.map { it.transform() })
                    onExecuteGetFavorites()
                }
            }
        }

    fun onExecuteGetFavorites() {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                val listFavoriteIds = getFavoriteDrinks().map { it.transform() }.map { it.id }
                list.map { drink -> drink.isFavorite = drink.id in listFavoriteIds }
                _state.value = HomeUiState.Success(list = list)
            }
        }
    }

    fun onTypeClicked(drinkType: DrinkType) {
        this.drinkType = drinkType
        onExecuteGetDrinksByType()
    }

    sealed class HomeUiState {
        object Idle : HomeUiState()
        data class Error(val error: ErrorVO) : HomeUiState()
        object Loading : HomeUiState()
        data class Success(val list: List<DrinkVO>) : HomeUiState()
    }

}