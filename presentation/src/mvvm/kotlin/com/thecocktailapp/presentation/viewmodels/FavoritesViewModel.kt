package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.presentation.base.CoreMVVMViewModel
import com.thecocktailapp.domain.usecases.common.GetFavoriteDrinks
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.ErrorVO
import com.thecocktailapp.presentation.vo.FavoritesSuccess
import com.thecocktailapp.presentation.vo.FavoritesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val getFavoriteDrinks: @JvmSuppressWildcards GetFavoriteDrinks) :
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
            onEmitNavigation(element = success?.drinks?.containsAll(success.initDrinks)?.not())
        }
    }

}