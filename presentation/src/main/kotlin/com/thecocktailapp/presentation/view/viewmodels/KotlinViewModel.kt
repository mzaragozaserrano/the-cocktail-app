package com.thecocktailapp.presentation.view.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.domain.utils.extension.toFlowResult
import com.mzaragozaserrano.presentation.view.base.BaseViewModel
import com.thecocktailapp.domain.usecases.ShowRandomDrink
import com.thecocktailapp.presentation.view.utils.mvi.CommonAction
import com.thecocktailapp.presentation.view.utils.mvi.CommonResult
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.KotlinAction
import com.thecocktailapp.presentation.view.utils.mvi.KotlinIntent
import com.thecocktailapp.presentation.view.utils.mvi.KotlinResult
import com.thecocktailapp.presentation.view.utils.mvi.KotlinTask
import com.thecocktailapp.presentation.view.utils.mvi.KotlinViewState
import com.thecocktailapp.presentation.view.utils.mvi.mapToAction
import com.thecocktailapp.presentation.view.utils.mvi.mapToState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KotlinViewModel @Inject constructor(
    private val showRandomDrink: @JvmSuppressWildcards ShowRandomDrink,
) : BaseViewModel<KotlinViewState, KotlinIntent>() {

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

        is KotlinAction.Task -> {
            onExecuteTask(action)
        }

    }

    private fun onInit() = KotlinResult.Init.toFlowResult()

    private fun onIdle() = CommonResult.Idle.toFlowResult()

    private fun onExecuteTask(task: KotlinAction.Task) = when (task) {
        is KotlinAction.Task.CheckPreferencesToShowRandomDrink -> {
            onExecuteShowRandomDrink()
        }
    }

    private fun onExecuteShowRandomDrink(): Flow<KotlinResult> =
        KotlinResult.Task.Success(
            if (showRandomDrink()) {
                KotlinTask.NavigateToSplashFragment
            } else {
                KotlinTask.NavigateToCocktailFragment
            }
        ).toFlowResult()

}