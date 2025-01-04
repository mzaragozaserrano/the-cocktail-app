package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.domain.bo.Result
import com.mzs.core.presentation.base.CoreMVVMViewModel
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrinkUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.GetDrinkById
import com.thecocktailapp.domain.usecases.detail.GetDrinkByIdUseCaseImpl
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrinkUseCaseImpl
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.DetailSuccess
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
    private val getDrinkById: @JvmSuppressWildcards GetDrinkById,
    private val removeFavoriteDrink: @JvmSuppressWildcards RemoveFavoriteDrink
) : CoreMVVMViewModel<DetailUiState>() {

    override fun createInitialState(): DetailUiState = DetailUiState()

    fun onExecuteGetDrinkById(idDrink: Int) {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                getDrinkById
                    .invoke(params = GetDrinkByIdUseCaseImpl.Params(id = idDrink))
                    .collect(::handleDrinkByIdResponse)
            }
        }
    }

    private suspend fun handleDrinkByIdResponse(result: Result<CocktailBO>) =
        withContext(context = Dispatchers.Main) {
            when (result) {
                is Result.Loading -> {
                    onUpdateUiState { copy(error = null, loading = true) }
                }

                is Result.Response.Error<*> -> {
                    onUpdateUiState {
                        copy(
                            error = (result.code as ErrorBO).transform(),
                            loading = false
                        )
                    }
                }

                is Result.Response.Success -> {
                    val drink = result.data.drinks.first().transform()
                    onUpdateUiState {
                        copy(
                            error = null,
                            loading = false,
                            success = DetailSuccess(drink = drink, isFavorite = drink.isFavorite)
                        )
                    }
                }
            }
        }


    fun addFavoriteDrink(drink: DrinkVO) {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                addFavoriteDrink.invoke(params = AddFavoriteDrinkUseCaseImpl.Params(drink = drink.transform()))
                onUpdateUiState { copy(success = success?.copy(isFavorite = true)) }
            }
        }
    }

    fun removeFavoriteDrink(drink: DrinkVO) {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                removeFavoriteDrink.invoke(params = RemoveFavoriteDrinkUseCaseImpl.Params(drink = drink.transform()))
                onUpdateUiState { copy(success = success?.copy(isFavorite = false)) }
            }
        }
    }

}