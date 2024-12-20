package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzs.core.domain.bo.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.splash.GetRandomDrink
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
class SplashViewModel @Inject constructor(
    val getRandomDrink: @JvmSuppressWildcards GetRandomDrink,
) : ViewModel() {

    private val _state = MutableStateFlow<SplashUiState>(value = SplashUiState.Idle)
    val state = _state.asStateFlow()

    init {
        onExecuteGetRandomDrink()
    }

    fun onExecuteGetRandomDrink() {
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
                    _state.value = SplashUiState.Loading
                }

                is Result.Response.Error<*> -> {
                    _state.value = SplashUiState.Error(error = (result.code as ErrorBO).transform())
                }

                is Result.Response.Success -> {
                    _state.value =
                        SplashUiState.Success(drink = result.data.drinks.first().transform())
                }
            }
        }

    sealed class SplashUiState {
        data object Idle : SplashUiState()
        data class Error(val error: ErrorVO) : SplashUiState()
        data object Loading : SplashUiState()
        data class Success(val drink: DrinkVO) : SplashUiState()
    }

}