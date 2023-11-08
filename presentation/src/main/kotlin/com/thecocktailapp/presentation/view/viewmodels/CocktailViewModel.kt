package com.thecocktailapp.presentation.view.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.presentation.view.base.BaseViewModel
import com.thecocktailapp.domain.utils.toFlowResult
import com.thecocktailapp.presentation.view.utils.mvi.CocktailAction
import com.thecocktailapp.presentation.view.utils.mvi.CocktailIntent
import com.thecocktailapp.presentation.view.utils.mvi.CocktailResult
import com.thecocktailapp.presentation.view.utils.mvi.CocktailViewState
import com.thecocktailapp.presentation.view.utils.mvi.CommonAction
import com.thecocktailapp.presentation.view.utils.mvi.CommonResult
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.mapToAction
import com.thecocktailapp.presentation.view.utils.mvi.mapToState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor() : BaseViewModel<CocktailViewState, CocktailIntent>() {

    init {
        handleIntent()
    }

    override fun createInitialState(): CocktailViewState = CommonViewState.Initialized()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handleIntent() {
        viewModelScope.launch {
            intentFlow
                .map { it.mapToAction() }
                .flatMapLatest { processAction(it) }
                .collect { collectState(it.mapToState()) }
        }
    }

    private fun processAction(action: CocktailAction) = when (action) {
        is CommonAction.Init -> {
            onInit()
        }

        is CommonAction.Idle -> {
            onIdle()
        }
    }

    private fun onInit() = CocktailResult.Init.toFlowResult()

    private fun onIdle() = CommonResult.Idle.toFlowResult()

}