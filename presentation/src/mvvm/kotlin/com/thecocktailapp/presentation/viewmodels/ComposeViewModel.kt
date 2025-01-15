package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.presentation.base.CoreMVVMViewModel
import com.thecocktailapp.domain.usecases.splash.ShowRandomDrinkUseCaseImpl
import com.thecocktailapp.presentation.vo.ComposeUiState
import kotlinx.coroutines.launch

class ComposeViewModel(private val showRandomDrink: ShowRandomDrinkUseCaseImpl) :
    CoreMVVMViewModel<ComposeUiState>() {

    override fun createInitialState(): ComposeUiState = ComposeUiState()

    fun onExecuteShowRandomDrink() {
        viewModelScope.launch {
            onUpdateUiState { copy(showRandomDrink = showRandomDrink()) }
        }
    }

}