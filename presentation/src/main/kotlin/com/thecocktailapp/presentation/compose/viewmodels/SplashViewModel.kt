package com.thecocktailapp.presentation.compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.GetRandomDrink
import com.thecocktailapp.presentation.common.utils.transform
import com.thecocktailapp.presentation.common.vo.ErrorVO
import com.thecocktailapp.presentation.view.utils.transform
import com.thecocktailapp.presentation.view.vo.DrinkVO
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

    private val _state = MutableStateFlow<SplashUiState>(SplashUiState.Idle)
    val state = _state.asStateFlow()

    init {
        onExecuteGetRandomDrink()
    }

    fun onExecuteGetRandomDrink() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
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
                    _state.value = SplashUiState.Error((result.code as ErrorBO).transform())
                }

                is Result.Response.Success -> {
                    _state.value = SplashUiState.Success(result.data.drinks.first().transform())
                }
            }
        }

    sealed class SplashUiState {
        object Idle : SplashUiState()
        data class Error(val error: ErrorVO) : SplashUiState()
        object Loading : SplashUiState()
        data class Success(val drink: DrinkVO) : SplashUiState()
    }

}