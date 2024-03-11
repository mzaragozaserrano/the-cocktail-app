package com.thecocktailapp.presentation.viewmodels.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.detail.GetDrinkById
import com.thecocktailapp.domain.usecases.detail.GetDrinkByIdUseCaseImpl
import com.thecocktailapp.presentation.utils.navigation.NavArg
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
class DetailDrinkViewModel @Inject constructor(
    private val getDrinkById: @JvmSuppressWildcards GetDrinkById,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = savedStateHandle.get<Int>(key = NavArg.DrinkId.key) ?: 0

    private val _state = MutableStateFlow<DetailDrinkUiState>(value = DetailDrinkUiState.Idle)
    val state = _state.asStateFlow()

    init {
        onExecuteGetDrinkById()
    }

    fun onExecuteGetDrinkById() {
        viewModelScope.launch {
            withContext(context = Dispatchers.IO) {
                getDrinkById
                    .invoke(params = GetDrinkByIdUseCaseImpl.Params(id = id))
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
                    _state.value =
                        DetailDrinkUiState.Error(error = (result.code as ErrorBO).transform())
                }

                is Result.Response.Success -> {
                    _state.value =
                        DetailDrinkUiState.Success(drink = result.data.drinks.first().transform())
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