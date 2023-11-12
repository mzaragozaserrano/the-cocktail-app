package com.thecocktailapp.presentation.compose.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecocktailapp.presentation.compose.utils.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailDrinkViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.DrinkId.key) ?: 0

    private val _state = MutableStateFlow(DetailDrinkUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = DetailDrinkUiState(id = id)
        }
    }

    data class DetailDrinkUiState(val id: Int = 0)

}