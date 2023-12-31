package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.domain.utils.extension.toFlowResult
import com.mzaragozaserrano.presentation.view.base.MVIViewModel
import com.thecocktailapp.domain.usecases.splash.ShowRandomDrink
import com.thecocktailapp.presentation.utils.mvi.CommonAction
import com.thecocktailapp.presentation.utils.mvi.CommonResult
import com.thecocktailapp.presentation.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.utils.mvi.KotlinAction
import com.thecocktailapp.presentation.utils.mvi.KotlinIntent
import com.thecocktailapp.presentation.utils.mvi.KotlinResult
import com.thecocktailapp.presentation.utils.mvi.KotlinTask
import com.thecocktailapp.presentation.utils.mvi.KotlinViewState
import com.thecocktailapp.presentation.utils.mvi.mapToAction
import com.thecocktailapp.presentation.utils.mvi.mapToState
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
) : MVIViewModel<KotlinViewState, KotlinIntent>() {

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

    private fun processAction(action: KotlinAction): Flow<KotlinResult> = when (action) {
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

    private fun onInit(): Flow<KotlinResult> = KotlinResult.Init.toFlowResult()

    private fun onIdle(): Flow<KotlinResult> = CommonResult.Idle.toFlowResult()

    private fun onExecuteTask(task: KotlinAction.Task): Flow<KotlinResult> = when (task) {
        is KotlinAction.Task.CheckPreferencesToShowRandomDrink -> {
            onExecuteShowRandomDrink()
        }
    }

    private fun onExecuteShowRandomDrink(): Flow<KotlinResult> =
        KotlinResult.Task.Success(
            if (showRandomDrink()) {
                KotlinTask.NavigateToSplashFragment
            } else {
                KotlinTask.NavigateToHomeFragment
            }
        ).toFlowResult()

}