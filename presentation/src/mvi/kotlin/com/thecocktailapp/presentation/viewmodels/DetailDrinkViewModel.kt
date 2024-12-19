package com.thecocktailapp.presentation.viewmodels

import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.utils.extensions.toFlowResult
import com.mzs.core.presentation.base.CoreMVIViewModel
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.detail.GetDrinkById
import com.thecocktailapp.domain.usecases.detail.GetDrinkByIdUseCaseImpl
import com.thecocktailapp.presentation.utils.CommonAction
import com.thecocktailapp.presentation.utils.CommonIntent
import com.thecocktailapp.presentation.utils.CommonResult
import com.thecocktailapp.presentation.utils.CommonViewState
import com.thecocktailapp.presentation.utils.DetailDrinkAction
import com.thecocktailapp.presentation.utils.DetailDrinkIntent
import com.thecocktailapp.presentation.utils.DetailDrinkResult
import com.thecocktailapp.presentation.utils.DetailDrinkTask
import com.thecocktailapp.presentation.utils.DetailDrinkViewState
import com.thecocktailapp.presentation.utils.transform
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DetailDrinkViewModel @Inject constructor(
    private val getDrinkById: @JvmSuppressWildcards GetDrinkById,
) : CoreMVIViewModel<DetailDrinkViewState, DetailDrinkIntent, DetailDrinkAction, DetailDrinkResult>() {

    private var drink: DrinkBO? = null

    override fun createInitialState(): DetailDrinkViewState = CommonViewState.Initialized()

    override fun DetailDrinkResult.mapToState(): DetailDrinkViewState =
        when (this) {
            is CommonResult.Idle -> {
                CommonViewState.Idle
            }

            is DetailDrinkResult.Init -> {
                if (drink != null) {
                    CommonViewState.Idle
                } else {
                    CommonViewState.Initialized()
                }
            }

            is DetailDrinkResult.Task.Error -> {
                DetailDrinkViewState.ShowError(idMessage = error.messageId)
            }

            is DetailDrinkResult.Task.Loading -> {
                DetailDrinkViewState.ShowProgressDialog
            }

            is DetailDrinkResult.Task.Success -> {
                when (task) {
                    is DetailDrinkTask.DrinkGotten -> {
                        DetailDrinkViewState.SetDrink(drink = task.drink.transform())
                    }
                }
            }
        }

    override suspend fun processAction(action: DetailDrinkAction): Flow<DetailDrinkResult> =
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

    override fun DetailDrinkIntent.mapToAction(): DetailDrinkAction =
        when (this) {
            is CommonIntent.Idle -> {
                CommonAction.Idle
            }

            is CommonIntent.Init -> {
                CommonAction.Init(refresh)
            }

            is DetailDrinkIntent.GetDrinkById -> {
                if (id != null) {
                    DetailDrinkAction.Task.GetDrinkById(id = id)
                } else {
                    //Error
                    CommonAction.Idle
                }
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