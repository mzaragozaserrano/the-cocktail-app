package com.thecocktailapp.presentation.viewmodels

import com.mzs.core.domain.utils.extensions.toFlowResult
import com.mzs.core.presentation.base.CoreMVIViewModel
import com.thecocktailapp.domain.usecases.splash.ShowRandomDrink
import com.thecocktailapp.presentation.utils.CommonAction
import com.thecocktailapp.presentation.utils.CommonIntent
import com.thecocktailapp.presentation.utils.CommonResult
import com.thecocktailapp.presentation.utils.CommonViewState
import com.thecocktailapp.presentation.utils.KotlinAction
import com.thecocktailapp.presentation.utils.KotlinIntent
import com.thecocktailapp.presentation.utils.KotlinResult
import com.thecocktailapp.presentation.utils.KotlinTask
import com.thecocktailapp.presentation.utils.KotlinViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class KotlinViewModel @Inject constructor(
    private val showRandomDrink: @JvmSuppressWildcards ShowRandomDrink,
) : CoreMVIViewModel<KotlinViewState, KotlinIntent, KotlinAction, KotlinResult>() {

    override fun createInitialState(): KotlinViewState = CommonViewState.Initialized()

    override fun KotlinResult.mapToState(): KotlinViewState =
        when (this) {
            is CommonResult.Idle -> {
                CommonViewState.Idle
            }

            is KotlinResult.Init -> {
                CommonViewState.Initialized()
            }

            is KotlinResult.Task.Success -> {
                when (task) {
                    is KotlinTask.NavigateToHomeFragment -> {
                        KotlinViewState.Navigate.ToHomeFragment
                    }

                    is KotlinTask.NavigateToSplashFragment -> {
                        KotlinViewState.Navigate.ToSplashFragment
                    }
                }
            }
        }

    override suspend fun processAction(action: KotlinAction): Flow<KotlinResult> = when (action) {
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

    override fun KotlinIntent.mapToAction(): KotlinAction =
        when (this) {
            is CommonIntent.Idle -> CommonAction.Idle
            is CommonIntent.Init -> CommonAction.Init(refresh)
            is KotlinIntent.ShowRandomDrink -> KotlinAction.Task.CheckPreferencesToShowRandomDrink
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