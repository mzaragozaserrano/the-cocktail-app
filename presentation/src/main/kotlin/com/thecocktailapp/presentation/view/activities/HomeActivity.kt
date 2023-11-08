package com.thecocktailapp.presentation.view.activities

import androidx.activity.viewModels
import com.mzaragozaserrano.presentation.view.base.BaseActivity
import com.thecocktailapp.presentation.view.utils.mvi.CommonIntent
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.HomeIntent
import com.thecocktailapp.presentation.view.utils.mvi.HomeViewState
import com.thecocktailapp.presentation.view.viewmodels.HomeViewModel
import com.thecocktailapp.presentation.view.vo.HomeVO
import com.thecocktailapp.ui.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewState, HomeIntent, ActivityHomeBinding, HomeViewModel>() {

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
                initComponents(homeVO = state.data as HomeVO)
            }

            is HomeViewState.Navigate.ToComposeModule -> {
                clearAndNavigateToNewActivity(ComposeActivity::class.java)
            }
            is  HomeViewState.Navigate.ToKotlinModule -> {
                clearAndNavigateToNewActivity(KotlinActivity::class.java)
            }
        }
    }

    private fun initComponents(homeVO: HomeVO) {
        with(binding) {
            buttonCompose.apply {
                initComponent(homeVO.buttonCompose)
                setOnButtonClicked {
                    emitAction(HomeIntent.GoToComposeModule)
                }
            }
            buttonKotlin.apply {
                initComponent(homeVO.buttonKotlin)
                setOnButtonClicked {
                    emitAction(HomeIntent.GoToKotlinModule)
                }
            }
        }
    }

}