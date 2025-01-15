package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.presentation.base.CoreMVVMViewModel
import com.thecocktailapp.domain.usecases.common.GetFavoriteDrinksUseCaseImpl
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.ErrorVO
import com.thecocktailapp.presentation.vo.FavoritesSuccess
import com.thecocktailapp.presentation.vo.FavoritesUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(private val getFavoriteDrinks: GetFavoriteDrinksUseCaseImpl) :
    CoreMVVMViewModel<FavoritesUiState>() {

    override fun createInitialState(): FavoritesUiState = FavoritesUiState()

    fun onExecuteGetFavorites() {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                val drinks = getFavoriteDrinks().map { it.transform(isFavorite = true) }
                if (drinks.isEmpty()) {
                    onUpdateUiState { copy(error = ErrorVO.FavoritesNotFound) }
                } else {
                    onUpdateUiState {
                        copy(
                            error = null,
                            success = FavoritesSuccess(drinks = drinks, initDrinks = drinks)
                        )
                    }
                }
            }
        }
    }

    fun onRefreshList(drinkId: Int) {
        with(getViewModelState()) {
            val drinks = success?.drinks?.toMutableList()
            drinks?.removeIf { drink -> drink.id == drinkId }
            if (drinks != null && drinks.isEmpty()) {
                onUpdateUiState {
                    copy(
                        error = null,
                        success = success?.copy(drinks = drinks)
                    )
                }
            } else {
                onUpdateUiState { copy(error = ErrorVO.FavoritesNotFound) }
            }
        }
    }

    fun onGoBack() {
        with(getViewModelState()) {
            onUpdateUiState { copy(error = null, loading = true) }
            onEmitNavigation(
                element = success?.drinks?.containsAll(success.initDrinks)?.not() ?: false
            )
        }
    }

}