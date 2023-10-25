package com.thecocktailapp.presentation.view.activities

import androidx.activity.viewModels
import com.mzaragozaserrano.presentation.view.base.BaseActivity
import com.mzaragozaserrano.presentation.view.utils.mvi.CommonViewState
import com.mzaragozaserrano.presentation.view.utils.mvi.HomeViewState
import com.mzaragozaserrano.presentation.view.vo.MinimalButtonVO
import com.mzaragozaserrano.ui.R
import com.mzaragozaserrano.ui.databinding.ActivityHomeBinding
import com.thecocktailapp.presentation.view.utils.mvi.HomeIntent
import com.thecocktailapp.presentation.view.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewState, HomeIntent, ActivityHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()
    override val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun renderView(state: HomeViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.Initialized -> {
                initComponents()
            }
        }
    }

    private fun initComponents() {
        with(binding) {
            icCompose.initComponent(
                MinimalButtonVO(
                    iconId = R.drawable.ic_compose,
                    textId = R.string.button_hello_compose
                ) {})
            icKotlin.initComponent(MinimalButtonVO(
                iconId = R.drawable.ic_kotlin,
                textId = R.string.button_hello_kotlin
            ) {})
        }
    }

}