package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.domain.bo.Result
import com.mzs.core.presentation.base.CoreMVVMViewModel
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.home.GetDrinksByTypeUseCaseImpl
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.DrinkType
import com.thecocktailapp.presentation.vo.HomeSuccess
import com.thecocktailapp.presentation.vo.HomeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val getDrinksByType: GetDrinksByTypeUseCaseImpl) :
    CoreMVVMViewModel<HomeUiState>() {

    override fun createInitialState(): HomeUiState = HomeUiState()

    fun onExecuteGetDrinksByType() {
        with(getViewModelState()) {
            viewModelScope.launch {
                withContext(context = Dispatchers.IO) {
                    getDrinksByType
                        .invoke(params = GetDrinksByTypeUseCaseImpl.Params(dbId = drinkType.dbId))
                        .collect(collector = ::handleDrinkByTypeResponse)
                }
            }
        }
    }

    fun onRefreshList(drinkId: Int) {
        onUpdateUiState {
            copy(
                success = success?.copy(
                    drinks = success.drinks.map { drink ->
                        if (drink.id == drinkId) {
                            drink.copy(isFavorite = !drink.isFavorite)
                        } else {
                            drink
                        }
                    }
                )
            )
        }
    }

    fun onRetryExecuteGetDrinksByType() {
        onUpdateUiState { copy(error = null) }
        onExecuteGetDrinksByType()
    }


    fun onTypeClicked(drinkType: DrinkType) {
        onUpdateUiState { copy(drinkType = drinkType) }
        onExecuteGetDrinksByType()
    }

    private suspend fun handleDrinkByTypeResponse(result: Result<List<DrinkBO>>) =
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
                    onUpdateUiState {
                        copy(
                            loading = false,
                            success = success?.copy(drinks = result.data.map { it.transform() })
                                ?: HomeSuccess(drinks = result.data.map { it.transform() })
                        )
                    }
                }
            }
        }

}