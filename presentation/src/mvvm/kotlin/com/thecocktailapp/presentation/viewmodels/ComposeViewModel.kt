package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.presentation.base.CoreMVVMViewModel
import com.thecocktailapp.domain.usecases.splash.ShowRandomDrink
import com.thecocktailapp.presentation.vo.ComposeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComposeViewModel @Inject constructor(private val showRandomDrink: @JvmSuppressWildcards ShowRandomDrink) :
    CoreMVVMViewModel<ComposeUiState>() {

    override fun createInitialState(): ComposeUiState = ComposeUiState()

    fun onExecuteShowRandomDrink() {
        viewModelScope.launch {
            onUpdateUiState { copy(showRandomDrink = showRandomDrink()) }
        }
    }

}