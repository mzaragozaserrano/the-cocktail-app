package com.thecocktailapp.presentation.compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecocktailapp.domain.usecases.ShowRandomDrink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComposeViewModel @Inject constructor(
    private val showRandomDrink: @JvmSuppressWildcards ShowRandomDrink,
) : ViewModel() {

    private val _state = MutableStateFlow(ComposeUiState())
    val state = _state.asStateFlow()

    init {
        onExecuteShowRandomDrink()
    }

    private fun onExecuteShowRandomDrink() {
        viewModelScope.launch {
            _state.value = ComposeUiState(showRandomDrink = showRandomDrink())
        }
    }

    data class ComposeUiState(val showRandomDrink: Boolean = false)

}