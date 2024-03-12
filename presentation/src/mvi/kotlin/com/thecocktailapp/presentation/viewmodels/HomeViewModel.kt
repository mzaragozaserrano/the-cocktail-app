package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.thecocktailapp.core.domain.utils.extension.toFlowResult
import presentation.base.MVIViewModel
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeAction
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeIntent
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeResult
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeViewState
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonAction
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonResult
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonViewState
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.mapToAction
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.mapToState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : MVIViewModel<HomeViewState, HomeIntent>() {

    init {
        handleIntent()
    }

    override fun createInitialState(): HomeViewState = CommonViewState.Initialized()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handleIntent() {
        viewModelScope.launch {
            intentFlow
                .map { it.mapToAction() }
                .flatMapLatest { processAction(it) }
                .collect { collectState(it.mapToState()) }
        }
    }

    private fun processAction(action: HomeAction) = when (action) {
        is CommonAction.Init -> {
            onInit()
        }

        is CommonAction.Idle -> {
            onIdle()
        }
    }

    private fun onInit() = HomeResult.Init.toFlowResult()

    private fun onIdle() = CommonResult.Idle.toFlowResult()

}