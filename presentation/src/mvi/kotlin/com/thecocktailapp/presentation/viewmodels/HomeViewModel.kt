package com.thecocktailapp.presentation.viewmodels

import com.mzs.core.domain.utils.extensions.toFlowResult
import com.mzs.core.presentation.base.CoreMVIViewModel
import com.thecocktailapp.presentation.utils.CommonAction
import com.thecocktailapp.presentation.utils.CommonIntent
import com.thecocktailapp.presentation.utils.CommonResult
import com.thecocktailapp.presentation.utils.CommonViewState
import com.thecocktailapp.presentation.utils.HomeAction
import com.thecocktailapp.presentation.utils.HomeIntent
import com.thecocktailapp.presentation.utils.HomeResult
import com.thecocktailapp.presentation.utils.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    CoreMVIViewModel<HomeViewState, HomeIntent, HomeAction, HomeResult>() {

    override fun createInitialState(): HomeViewState = CommonViewState.Initialized()

    override fun HomeResult.mapToState(): HomeViewState =
        when (this) {
            is CommonResult.Idle -> {
                CommonViewState.Idle
            }

            is HomeResult.Task.Error -> {
                HomeViewState.ShowError(idMessage = error.messageId)
            }

            is HomeResult.Init -> {
                CommonViewState.Initialized()
            }

            is HomeResult.Task.Loading -> {
                HomeViewState.ShowProgressDialog
            }
        }

    override suspend fun processAction(action: HomeAction): Flow<HomeResult> = when (action) {
        is CommonAction.Init -> {
            onInit()
        }

        is CommonAction.Idle -> {
            onIdle()
        }
    }

    override fun HomeIntent.mapToAction(): HomeAction =
        when (this) {
            is CommonIntent.Idle -> {
                CommonAction.Idle
            }

            is CommonIntent.Init -> {
                CommonAction.Init(refresh)
            }
        }

    private fun onInit(): Flow<HomeResult> = HomeResult.Init.toFlowResult()

    private fun onIdle(): Flow<HomeResult> = CommonResult.Idle.toFlowResult()

}