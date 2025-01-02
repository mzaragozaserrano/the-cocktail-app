package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mzs.core.domain.bo.Result
import com.mzs.core.presentation.base.CoreMVVMViewModel
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
    private val isFavoriteDrink: @JvmSuppressWildcards IsFavoriteDrink,
    private val removeFavoriteDrink: @JvmSuppressWildcards RemoveFavoriteDrink,
    savedStateHandle: SavedStateHandle,
) : CoreMVVMViewModel<DetailUiState>() {

    override fun createInitialState(): DetailUiState = DetailUiState()

    init {
        onExecuteGetDrinkById(id = savedStateHandle.get<String>(key = NavArg.DrinkId.key) ?: "0")
    }

    fun onExecuteGetDrinkById(id: String) {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                getDrinkById
                    .invoke(params = GetDrinkByIdUseCaseImpl.Params(id = id.toInt()))
                    .collect(::handleDrinkByIdResponse)
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

    private suspend fun handleDrinkByIdResponse(result: Result<CocktailBO>) =
        withContext(Dispatchers.Main) {
            when (result) {
                is Result.Loading -> {
                    onUpdateUiState { copy(loading = true, error = null) }
                }

                is Result.Response.Error<*> -> {
                    onUpdateUiState {
                        copy(
                            loading = false,
                            error = (result.code as ErrorBO).transform()
                        )
                    }
                }

                is Result.Response.Success -> {
                    onExecuteIsFavoriteDrink(drink = result.data.drinks.first().transform())
                }
            }
        }

    private fun onExecuteIsFavoriteDrink(drink: DrinkVO) {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                isFavoriteDrink.invoke(IsFavoriteDrinkUseCaseImpl.Params(drinkId = drink.id.toInt()))
                    .apply {
                        onUpdateUiState {
                            copy(
                                loading = false,
                                error = null,
                                success = DetailSuccess(
                                    drink = drink,
                                    isFavorite = drink.isFavorite
                                )
                            )
                        }
                    }
            }
        }
    }

}