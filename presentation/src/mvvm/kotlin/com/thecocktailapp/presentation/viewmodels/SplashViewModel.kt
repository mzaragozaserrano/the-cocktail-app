package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.domain.bo.Result
import com.mzs.core.presentation.base.CoreMVVMViewModel
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.splash.GetRandomDrink
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.SplashSuccess
import com.thecocktailapp.presentation.vo.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val getRandomDrink: @JvmSuppressWildcards GetRandomDrink,
) : CoreMVVMViewModel<SplashUiState>() {

    init {
        onExecuteGetRandomDrink()
    }

    override fun createInitialState(): SplashUiState = SplashUiState()

    fun onRetryExecuteGetRandomDrink() {
        onUpdateUiState { copy(error = null) }
        onExecuteGetRandomDrink()
    }

    private fun onExecuteGetRandomDrink() {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                getRandomDrink().collect(::handleRandomDrinkResponse)
            }
        }
    }

    private suspend fun handleRandomDrinkResponse(result: Result<CocktailBO>) =
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
                    onUpdateUiState {
                        copy(
                            loading = false,
                            error = null,
                            success = SplashSuccess(drink = result.data.drinks.first().transform())
                        )
                    }
                }
            }
        }


}