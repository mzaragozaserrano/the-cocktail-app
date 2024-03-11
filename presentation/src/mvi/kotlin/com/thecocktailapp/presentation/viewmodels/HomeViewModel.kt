package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.domain.utils.extension.toFlowResult
import com.mzs.core.presentation.view.base.MVIViewModel
import com.thecocktailapp.presentation.utils.mvi.HomeAction
import com.thecocktailapp.presentation.utils.mvi.HomeIntent
import com.thecocktailapp.presentation.utils.mvi.HomeResult
import com.thecocktailapp.presentation.utils.mvi.HomeViewState
import com.thecocktailapp.presentation.utils.mvi.CommonAction
import com.thecocktailapp.presentation.utils.mvi.CommonResult
import com.thecocktailapp.presentation.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.utils.mvi.mapToAction
import com.thecocktailapp.presentation.utils.mvi.mapToState
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