package com.thecocktailapp.presentation.view.activities

import androidx.activity.viewModels
import com.mzaragozaserrano.ui.databinding.ActivityKotlinBinding
import com.thecocktailapp.presentation.view.base.TheCocktailAppBaseActivity
import com.thecocktailapp.presentation.view.utils.mvi.CommonIntent
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.KotlinIntent
import com.thecocktailapp.presentation.view.utils.mvi.KotlinViewState
import com.thecocktailapp.presentation.view.viewmodels.KotlinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KotlinActivity :
    TheCocktailAppBaseActivity<KotlinViewState, KotlinIntent, ActivityKotlinBinding, KotlinViewModel>() {

    override val viewModel: KotlinViewModel by viewModels()
    override val binding by lazy { ActivityKotlinBinding.inflate(layoutInflater) }

    override fun onPause() {
        super.onPause()
        emitAction(CommonIntent.Idle)
    }

    override fun onStart() {
        super.onStart()
        emitAction(CommonIntent.Init())
    }

    override fun renderView(state: KotlinViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.Initialized -> {
            }
        }
    }

}