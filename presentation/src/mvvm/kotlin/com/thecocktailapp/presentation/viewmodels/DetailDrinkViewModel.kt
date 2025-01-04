package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mzs.core.presentation.base.CoreMVVMViewModel
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrinkUseCaseImpl
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.DetailUiState
import com.thecocktailapp.presentation.vo.DrinkVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailDrinkViewModel @Inject constructor(
    private val addFavoriteDrink: @JvmSuppressWildcards AddFavoriteDrink,
    private val removeFavoriteDrink: @JvmSuppressWildcards RemoveFavoriteDrink
) : CoreMVVMViewModel<DetailUiState>(), ViewModelProvider.Factory {

    override fun createInitialState(): DetailUiState = DetailUiState()

    fun setUpView(drink: DrinkVO) {
        onUpdateUiState { copy(isFavorite = drink.isFavorite) }
    }

    fun addFavoriteDrink(drink: DrinkVO) {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                addFavoriteDrink.invoke(params = AddFavoriteDrinkUseCaseImpl.Params(drink = drink.transform()))
                onUpdateUiState { copy(isFavorite = true) }
            }
        }
    }

    fun removeFavoriteDrink(drink: DrinkVO) {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                removeFavoriteDrink.invoke(params = RemoveFavoriteDrinkUseCaseImpl.Params(drink = drink.transform()))
                onUpdateUiState { copy(isFavorite = false) }
            }
        }
    }

}