package com.thecocktailapp.presentation.view.activities

import androidx.activity.viewModels
import com.mzaragozaserrano.ui.databinding.ActivityHomeBinding
import com.thecocktailapp.presentation.view.base.TheCocktailAppBaseActivity
import com.thecocktailapp.presentation.view.utils.mvi.CommonIntent
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.HomeIntent
import com.thecocktailapp.presentation.view.utils.mvi.HomeViewState
import com.thecocktailapp.presentation.view.viewmodels.HomeViewModel
import com.thecocktailapp.presentation.view.vo.HomeVO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : TheCocktailAppBaseActivity<HomeViewState, HomeIntent, ActivityHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()
    override val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onPause() {
        super.onPause()
        emitAction(CommonIntent.Idle)
    }

    override fun onStart() {
        super.onStart()
        emitAction(CommonIntent.Init())
    }

    override fun renderView(state: HomeViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.Initialized -> {
                initComponents(data = state.data as HomeVO)
            }

            is HomeViewState.Navigate -> {
                navigateTo(state = state)
            }
        }
    }

    private fun initComponents(data: HomeVO) {
        with(binding) {
            buttonCompose.apply {
                initComponent(data.buttonCompose)
                setOnButtonClicked { }
            }
            buttonKotlin.apply {
                initComponent(data.buttonKotlin)
                setOnButtonClicked {
                    emitAction(HomeIntent.NavigateToKotlinModule)
                }
            }
        }
    }

    private fun navigateTo(state: HomeViewState.Navigate) {
        when (state) {
            is HomeViewState.Navigate.ToKotlinModule -> {
                navigateToKotlinModule()
            }
        }
    }

}