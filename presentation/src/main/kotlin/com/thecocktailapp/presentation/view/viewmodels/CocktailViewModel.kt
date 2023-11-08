package com.thecocktailapp.presentation.view.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.presentation.view.base.BaseViewModel
import com.thecocktailapp.domain.bo.Result
import com.thecocktailapp.domain.usecases.GetRandomCocktail
import com.thecocktailapp.domain.utils.toFlowResult
import com.thecocktailapp.presentation.common.utils.transform
import com.thecocktailapp.presentation.view.utils.mvi.CocktailAction
import com.thecocktailapp.presentation.view.utils.mvi.CocktailIntent
import com.thecocktailapp.presentation.view.utils.mvi.CocktailResult
import com.thecocktailapp.presentation.view.utils.mvi.CocktailTask
import com.thecocktailapp.presentation.view.utils.mvi.CocktailViewState
import com.thecocktailapp.presentation.view.utils.mvi.CommonAction
import com.thecocktailapp.presentation.view.utils.mvi.CommonResult
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
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
class CocktailViewModel @Inject constructor(
    private val getRandomCocktail: @JvmSuppressWildcards GetRandomCocktail,
) : BaseViewModel<CocktailViewState, CocktailIntent>() {

    init {
        handleIntent()
    }

    override fun createInitialState(): CocktailViewState = CommonViewState.SetUpView()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handleIntent() {
        viewModelScope.launch {
            intentFlow
                .map { it.mapToAction() }
                .flatMapLatest { processAction(it) }
                .collect { collectState(it.mapToState()) }
        }
    }

    private suspend fun processAction(action: CocktailAction) = when (action) {
        is CommonAction.Init -> {
            onInit()
        }

        is CommonAction.Idle -> {
            onIdle()
        }

        is CocktailAction.Task -> {
            onExecuteTask(action)
        }
    }

    private fun onInit() = CocktailResult.Init.toFlowResult()

    private fun onIdle() = CommonResult.Idle.toFlowResult()

    private suspend fun onExecuteTask(task: CocktailAction.Task) = when (task) {
        is CocktailAction.Task.GetRandomCocktail -> {
            onExecuteGetRandomCocktail()
        }
    }

    private suspend fun onExecuteGetRandomCocktail(): Flow<CocktailResult> =
        getRandomCocktail().map { result ->
            when (result) {
                is Result.Loading -> {
                    CocktailResult.Task.Loading
                }

                is Result.Response.Error -> {
                    CocktailResult.Task.Error(result.code.transform())
                }

                is Result.Response.Success -> {
                    CocktailResult.Task.Success(CocktailTask.RandomCocktailGotten(result.data))
                }
            }
        }

}