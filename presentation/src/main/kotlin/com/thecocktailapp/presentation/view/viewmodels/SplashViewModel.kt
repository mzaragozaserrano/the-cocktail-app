package com.thecocktailapp.presentation.view.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.domain.utils.Result
import com.mzaragozaserrano.domain.utils.extension.toFlowResult
import com.mzaragozaserrano.presentation.view.base.BaseViewModel
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.GetRandomDrink
import com.thecocktailapp.presentation.common.utils.transform
import com.thecocktailapp.presentation.view.utils.mvi.CommonAction
import com.thecocktailapp.presentation.view.utils.mvi.CommonResult
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.SplashAction
import com.thecocktailapp.presentation.view.utils.mvi.SplashIntent
import com.thecocktailapp.presentation.view.utils.mvi.SplashResult
import com.thecocktailapp.presentation.view.utils.mvi.SplashTask
import com.thecocktailapp.presentation.view.utils.mvi.SplashTask.NavigateToCocktailFragment
import com.thecocktailapp.presentation.view.utils.mvi.SplashTask.NavigateToDrinkDetail
import com.thecocktailapp.presentation.view.utils.mvi.SplashViewState
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
class SplashViewModel @Inject constructor(
    private val getRandomDrink: @JvmSuppressWildcards GetRandomDrink,
) : BaseViewModel<SplashViewState, SplashIntent>() {

    private var drink: DrinkBO? = null

    init {
        handleIntent()
    }

    override fun createInitialState(): SplashViewState = CommonViewState.Initialized()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handleIntent() {
        viewModelScope.launch {
            intentFlow
                .map { it.mapToAction() }
                .flatMapLatest { processAction(it) }
                .collect { collectState(it.mapToState()) }
        }
    }

    private suspend fun processAction(action: SplashAction) = when (action) {
        is CommonAction.Init -> {
            onInit()
        }

        is CommonAction.Idle -> {
            onIdle()
        }

        is SplashAction.Task -> {
            onExecuteTask(action)
        }

        is SplashAction.TaskForNavigate -> {
            onNavigate(action)
        }
    }

    private fun onInit() = SplashResult.Init(drink).toFlowResult()

    private fun onIdle() = CommonResult.Idle.toFlowResult()

    private suspend fun onExecuteTask(task: SplashAction.Task) = when (task) {
        is SplashAction.Task.GetRandomDrink -> {
            onExecuteGetRandomDrink()
        }
    }

    private suspend fun onExecuteGetRandomDrink(): Flow<SplashResult> =
        getRandomDrink().map { result ->
            when (result) {
                is Result.Loading -> {
                    SplashResult.Task.Loading
                }

                is Result.Response.Error<*> -> {
                    SplashResult.Task.Error((result.code as ErrorBO).transform())
                }

                is Result.Response.Success -> {
                    drink = result.data.drinks.first()
                    SplashResult.Task.Success(SplashTask.RandomCocktailGotten(result.data.drinks.first()))
                }
            }
        }

    private fun onNavigate(action: SplashAction.TaskForNavigate) = when (action) {
        is SplashAction.TaskForNavigate.ToDrinkDetail -> {
            SplashResult.Task.Success(NavigateToDrinkDetail).toFlowResult()
        }

        is SplashAction.TaskForNavigate.ToMain -> {
            SplashResult.Task.Success(NavigateToCocktailFragment).toFlowResult()
        }
    }

}