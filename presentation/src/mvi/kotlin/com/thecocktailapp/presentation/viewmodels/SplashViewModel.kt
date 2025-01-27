package com.thecocktailapp.presentation.viewmodels

import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.utils.extensions.toFlowResult
import com.mzs.core.presentation.base.CoreMVIViewModel
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.usecases.splash.GetRandomDrinkUseCaseImpl
import com.thecocktailapp.presentation.utils.CommonAction
import com.thecocktailapp.presentation.utils.CommonIntent
import com.thecocktailapp.presentation.utils.CommonResult
import com.thecocktailapp.presentation.utils.CommonViewState
import com.thecocktailapp.presentation.utils.SplashAction
import com.thecocktailapp.presentation.utils.SplashIntent
import com.thecocktailapp.presentation.utils.SplashResult
import com.thecocktailapp.presentation.utils.SplashTask
import com.thecocktailapp.presentation.utils.SplashViewState
import com.thecocktailapp.presentation.utils.transform
import com.thecocktailapp.presentation.vo.ErrorVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SplashViewModel(private val getRandomDrink: GetRandomDrinkUseCaseImpl) :
    CoreMVIViewModel<SplashViewState, SplashIntent, SplashAction, SplashResult>() {

    private var drink: DrinkBO? = null

    override fun createInitialState(): SplashViewState = CommonViewState.Initialized()

    override fun SplashResult.mapToState(): SplashViewState =
        when (this) {
            is CommonResult.Idle -> {
                CommonViewState.Idle
            }

            is SplashResult.Init -> {
                if (drink != null) {
                    CommonViewState.Idle
                } else {
                    CommonViewState.Initialized()
                }
            }

            is SplashResult.Task.Error -> {
                SplashViewState.ShowError(idMessage = error.messageId)
            }

            is SplashResult.Task.Loading -> {
                SplashViewState.ShowProgressDialog
            }

            is SplashResult.Task.Success -> {
                when (task) {
                    is SplashTask.NavigateToDrinkDetail -> {
                        SplashViewState.Navigate.ToDrinkDetail(id = task.id)
                    }

                    is SplashTask.NavigateToHomeFragment -> {
                        SplashViewState.Navigate.ToHomeFragment
                    }

                    is SplashTask.DrinkGotten -> {
                        SplashViewState.ShowView(drink = task.drink.transform())
                    }
                }
            }
        }

    override suspend fun processAction(action: SplashAction): Flow<SplashResult> = when (action) {
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

    override fun SplashIntent.mapToAction(): SplashAction =
        when (this) {
            is CommonIntent.Idle -> {
                CommonAction.Idle
            }

            is CommonIntent.Init -> {
                CommonAction.Init(refresh)
            }

            is SplashIntent.GetRandomDrink -> {
                SplashAction.Task.GetRandomDrink
            }

            is SplashIntent.GoToDrinkDetail -> {
                SplashAction.TaskForNavigate.ToDrinkDetail
            }

            is SplashIntent.GoToMain -> {
                SplashAction.TaskForNavigate.ToMain
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
                    SplashResult.Task.Success(SplashTask.NavigateToDrinkDetail(id.toInt()))
                        .toFlowResult()
                } else {
                    SplashResult.Task.Error(ErrorVO.DataNotFound).toFlowResult()
                }
            }

            is SplashAction.TaskForNavigate.ToMain -> {
                SplashResult.Task.Success(SplashTask.NavigateToHomeFragment).toFlowResult()
            }
        }

}