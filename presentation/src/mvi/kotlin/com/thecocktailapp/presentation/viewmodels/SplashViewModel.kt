package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzs.core.domain.utils.Result
import com.mzs.core.domain.utils.extension.toFlowResult
import com.mzs.core.presentation.view.base.MVIViewModel
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.splash.GetRandomDrink
import com.thecocktailapp.presentation.utils.mvi.CommonAction
import com.thecocktailapp.presentation.utils.mvi.CommonResult
import com.thecocktailapp.presentation.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.utils.mvi.SplashAction
import com.thecocktailapp.presentation.utils.mvi.SplashIntent
import com.thecocktailapp.presentation.utils.mvi.SplashResult
import com.thecocktailapp.presentation.utils.mvi.SplashTask
import com.thecocktailapp.presentation.utils.mvi.SplashTask.NavigateToDrinkDetail
import com.thecocktailapp.presentation.utils.mvi.SplashTask.NavigateToHomeFragment
import com.thecocktailapp.presentation.utils.mvi.SplashViewState
import com.thecocktailapp.presentation.utils.mvi.mapToAction
import com.thecocktailapp.presentation.utils.mvi.mapToState
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.ErrorVO
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
) : MVIViewModel<SplashViewState, SplashIntent>() {

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

    private suspend fun processAction(action: SplashAction): Flow<SplashResult> = when (action) {
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

    private fun onInit(): Flow<SplashResult> = SplashResult.Init(drink).toFlowResult()

    private fun onIdle(): Flow<SplashResult> = CommonResult.Idle.toFlowResult()

    private suspend fun onExecuteTask(task: SplashAction.Task): Flow<SplashResult> = when (task) {
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
                    SplashResult.Task.Success(SplashTask.DrinkGotten(result.data.drinks.first()))
                }
            }
        }

    private fun onNavigate(action: SplashAction.TaskForNavigate): Flow<SplashResult> =
        when (action) {
            is SplashAction.TaskForNavigate.ToDrinkDetail -> {
                val id = drink?.id
                if (id != null) {
                    SplashResult.Task.Success(NavigateToDrinkDetail(id.toInt())).toFlowResult()
                } else {
                    SplashResult.Task.Error(ErrorVO.DataNotFound).toFlowResult()
                }
            }

            is SplashAction.TaskForNavigate.ToMain -> {
                SplashResult.Task.Success(NavigateToHomeFragment).toFlowResult()
            }
        }

}