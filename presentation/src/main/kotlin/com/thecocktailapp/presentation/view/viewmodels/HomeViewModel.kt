package com.thecocktailapp.presentation.view.viewmodels

import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.domain.utils.toFlowResult
import com.mzaragozaserrano.presentation.view.base.BaseViewModel
import com.mzaragozaserrano.presentation.view.vo.MinimalButtonVO
import com.mzaragozaserrano.ui.R
import com.thecocktailapp.presentation.view.utils.mvi.CommonAction
import com.thecocktailapp.presentation.view.utils.mvi.CommonResult
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.HomeAction
import com.thecocktailapp.presentation.view.utils.mvi.HomeIntent
import com.thecocktailapp.presentation.view.utils.mvi.HomeResult
import com.thecocktailapp.presentation.view.utils.mvi.HomeViewState
import com.thecocktailapp.presentation.view.utils.mvi.mapToAction
import com.thecocktailapp.presentation.view.utils.mvi.mapToState
import com.thecocktailapp.presentation.view.vo.HomeVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeViewState, HomeIntent>() {

    init {
        handleIntent()
    }

    override fun createInitialState(): HomeViewState = CommonViewState.Initialized(
        HomeVO(
            buttonCompose = MinimalButtonVO(
                iconId = R.drawable.ic_compose,
                textId = R.string.button_hello_compose
            ),
            buttonKotlin = MinimalButtonVO(
                iconId = R.drawable.ic_kotlin,
                textId = R.string.button_hello_kotlin
            )
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handleIntent() {
        viewModelScope.launch {
            intentFlow
                .map { it.mapToAction() }
                .flatMapLatest { processAction(it) }
                .collect { collectState(it.mapToState()) }
        }
    }

    private fun processAction(action: HomeAction) = when (action) {
        is CommonAction.Init -> {
            onInit()
        }

        is CommonAction.Idle -> {
            onIdle()
        }

        is HomeAction.TaskForNavigate -> {
            onNavigate(action)
        }
    }

    private fun onInit() = HomeResult.Init(
        buttonCompose = MinimalButtonVO(
            iconId = R.drawable.ic_compose,
            textId = R.string.button_hello_compose
        ),
        buttonKotlin = MinimalButtonVO(
            iconId = R.drawable.ic_kotlin,
            textId = R.string.button_hello_kotlin
        )
    ).toFlowResult()

    private fun onIdle() = CommonResult.Idle.toFlowResult()

    private fun onNavigate(action: HomeAction.TaskForNavigate) = when (action) {
        is HomeAction.TaskForNavigate.ToComposeModule -> {
            HomeResult.Task.Success.GoToComposeModule.toFlowResult()
        }
        is HomeAction.TaskForNavigate.ToKotlinModule -> {
            HomeResult.Task.Success.GoToKotlinModule.toFlowResult()
        }
    }

}