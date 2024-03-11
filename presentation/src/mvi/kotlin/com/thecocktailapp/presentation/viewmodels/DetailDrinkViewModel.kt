package com.thecocktailapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.core.domain.utils.extension.toFlowResult
import presentation.base.MVIViewModel
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.detail.GetDrinkById
import com.thecocktailapp.domain.usecases.detail.GetDrinkByIdUseCaseImpl
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.utils.mvi.CommonAction
import com.thecocktailapp.presentation.utils.mvi.CommonResult
import com.thecocktailapp.presentation.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.utils.mvi.DetailDrinkAction
import com.thecocktailapp.presentation.utils.mvi.DetailDrinkIntent
import com.thecocktailapp.presentation.utils.mvi.DetailDrinkResult
import com.thecocktailapp.presentation.utils.mvi.DetailDrinkTask
import com.thecocktailapp.presentation.utils.mvi.DetailDrinkViewState
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
class DetailDrinkViewModel @Inject constructor(
    private val getDrinkById: @JvmSuppressWildcards GetDrinkById,
) : MVIViewModel<DetailDrinkViewState, DetailDrinkIntent>() {

    private var drink: DrinkBO? = null

    init {
        handleIntent()
    }

    override fun createInitialState(): DetailDrinkViewState = CommonViewState.Initialized()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handleIntent() {
        viewModelScope.launch {
            intentFlow
                .map { it.mapToAction() }
                .flatMapLatest { processAction(it) }
                .collect { collectState(it.mapToState()) }
        }
    }

    private suspend fun processAction(action: DetailDrinkAction): Flow<DetailDrinkResult> =
        when (action) {
            is CommonAction.Init -> {
                onInit()
            }

            is CommonAction.Idle -> {
                onIdle()
            }

            is DetailDrinkAction.Task -> {
                onExecuteTask(action)
            }

        }

    private fun onInit(): Flow<DetailDrinkResult> = DetailDrinkResult.Init(drink).toFlowResult()

    private fun onIdle(): Flow<DetailDrinkResult> = CommonResult.Idle.toFlowResult()

    private suspend fun onExecuteTask(task: DetailDrinkAction.Task): Flow<DetailDrinkResult> =
        when (task) {
            is DetailDrinkAction.Task.GetDrinkById -> {
                onExecuteGetRandomDrink(id = task.id)
            }
        }

    private suspend fun onExecuteGetRandomDrink(id: Int): Flow<DetailDrinkResult> =
        getDrinkById(GetDrinkByIdUseCaseImpl.Params(id = id)).map { result ->
            when (result) {
                is Result.Loading -> {
                    DetailDrinkResult.Task.Loading
                }

                is Result.Response.Error<*> -> {
                    DetailDrinkResult.Task.Error((result.code as ErrorBO).transform())
                }

                is Result.Response.Success -> {
                    drink = result.data.drinks.first()
                    DetailDrinkResult.Task.Success(DetailDrinkTask.DrinkGotten(result.data.drinks.first()))
                }
            }
        }

}