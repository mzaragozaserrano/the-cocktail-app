package com.thecocktailapp.presentation.view.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.presentation.view.base.BaseViewModel
import com.thecocktailapp.domain.utils.toFlowResult
import com.thecocktailapp.presentation.view.utils.mvi.CommonAction
import com.thecocktailapp.presentation.view.utils.mvi.CommonResult
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.KotlinAction
import com.thecocktailapp.presentation.view.utils.mvi.KotlinIntent
import com.thecocktailapp.presentation.view.utils.mvi.KotlinResult
import com.thecocktailapp.presentation.view.utils.mvi.KotlinViewState
import com.thecocktailapp.presentation.view.utils.mvi.mapToAction
import com.thecocktailapp.presentation.view.utils.mvi.mapToState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KotlinViewModel @Inject constructor() : BaseViewModel<KotlinViewState, KotlinIntent>() {

    init {
        handleIntent()
    }

    override fun createInitialState(): KotlinViewState = CommonViewState.Initialized()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handleIntent() {
        viewModelScope.launch {
            intentFlow
                .map { it.mapToAction() }
                .flatMapLatest { processAction(it) }
                .collect { collectState(it.mapToState()) }
        }
    }

    private fun processAction(action: KotlinAction) = when (action) {
        is CommonAction.Init -> {
            onInit()
        }

        is CommonAction.Idle -> {
            onIdle()
        }

    }

    private fun onInit() = KotlinResult.Init.toFlowResult()

    private fun onIdle() = CommonResult.Idle.toFlowResult()

}