package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.domain.bo.Result
import com.mzs.core.presentation.base.CoreMVVMViewModel
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.splash.GetRandomDrinkUseCaseImpl
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.SplashSuccess
import com.thecocktailapp.presentation.vo.SplashUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(val getRandomDrink: GetRandomDrinkUseCaseImpl) :
    CoreMVVMViewModel<SplashUiState>() {

    override fun createInitialState(): SplashUiState = SplashUiState()

    fun onExecuteGetRandomDrink() {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                getRandomDrink().collect(::handleRandomDrinkResponse)
            }
        }
    }

    fun onRetryExecuteGetRandomDrink() {
        onUpdateUiState { copy(error = null) }
        onExecuteGetRandomDrink()
    }

    private suspend fun handleRandomDrinkResponse(result: Result<CocktailBO>) =
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
                            error = null,
                            loading = false,
                            success = SplashSuccess(drink = result.data.drinks.first().transform())
                        )
                    }
                }
            }
        }


}