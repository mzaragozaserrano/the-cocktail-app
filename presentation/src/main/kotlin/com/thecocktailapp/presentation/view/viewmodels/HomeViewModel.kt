package com.thecocktailapp.presentation.view.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.domain.utils.toFlowResult
import com.mzaragozaserrano.presentation.view.base.BaseViewModel
import com.mzaragozaserrano.presentation.view.utils.mvi.CommonViewState
import com.mzaragozaserrano.presentation.view.utils.mvi.HomeViewState
import com.thecocktailapp.presentation.view.utils.mvi.CommonAction
import com.thecocktailapp.presentation.view.utils.mvi.CommonResult
import com.thecocktailapp.presentation.view.utils.mvi.HomeAction
import com.thecocktailapp.presentation.view.utils.mvi.HomeIntent
import com.thecocktailapp.presentation.view.utils.mvi.HomeResult
import com.thecocktailapp.presentation.view.utils.mvi.mapToAction
import com.thecocktailapp.presentation.view.utils.mvi.mapToState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): BaseViewModel<HomeViewState, HomeIntent>() {

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

    private suspend fun processAction(action: HomeAction) = when (action) {
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