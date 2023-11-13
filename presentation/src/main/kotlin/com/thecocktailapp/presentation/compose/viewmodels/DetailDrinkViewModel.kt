package com.thecocktailapp.presentation.compose.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.GetDrinkById
import com.thecocktailapp.domain.usecases.GetDrinkByIdUseCaseImpl
import com.thecocktailapp.presentation.common.utils.transform
import com.thecocktailapp.presentation.common.vo.ErrorVO
import com.thecocktailapp.presentation.compose.utils.navigation.NavArg
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
class DetailDrinkViewModel @Inject constructor(
    private val getDrinkById: @JvmSuppressWildcards GetDrinkById,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.DrinkId.key) ?: 0

    private val _state = MutableStateFlow<DetailDrinkUiState>(DetailDrinkUiState.Idle)
    val state = _state.asStateFlow()

    init {
        onExecuteGetDrinkById()
    }

    fun onExecuteGetDrinkById() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getDrinkById
                    .invoke(GetDrinkByIdUseCaseImpl.Params(id))
                    .collect(::handleDrinkByIdResponse)
            }
        }
    }

    private suspend fun handleDrinkByIdResponse(result: Result<CocktailBO>) =
        withContext(Dispatchers.Main) {
            when (result) {
                is Result.Loading -> {
                    _state.value = DetailDrinkUiState.Loading
                }

                is Result.Response.Error<*> -> {
                    _state.value = DetailDrinkUiState.Error((result.code as ErrorBO).transform())
                }

                is Result.Response.Success -> {
                    _state.value = DetailDrinkUiState.Success(result.data.drinks.first().transform())
                }
            }
        }

    sealed class DetailDrinkUiState {
        object Idle : DetailDrinkUiState()
        data class Error(val error: ErrorVO) : DetailDrinkUiState()
        object Loading : DetailDrinkUiState()
        data class Success(val drink: DrinkVO) : DetailDrinkUiState()
    }

}