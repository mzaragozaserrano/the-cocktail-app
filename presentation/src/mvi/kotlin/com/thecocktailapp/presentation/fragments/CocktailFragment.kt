package com.thecocktailapp.presentation.fragments

import androidx.fragment.app.viewModels
import com.mzaragozaserrano.presentation.databinding.CoreToolbarMenuBinding
import com.mzaragozaserrano.presentation.view.base.BaseFragment
import com.mzaragozaserrano.presentation.view.utils.extensions.setUpMenuToolbar
import com.mzaragozaserrano.presentation.view.utils.extensions.showProgressDialog
import com.mzaragozaserrano.presentation.view.utils.viewBinding.viewBinding
import com.thecocktailapp.presentation.activities.KotlinActivity
import com.thecocktailapp.presentation.utils.mvi.CocktailIntent
import com.thecocktailapp.presentation.utils.mvi.CocktailViewState
import com.thecocktailapp.presentation.utils.mvi.CommonIntent
import com.thecocktailapp.presentation.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.viewmodels.CocktailViewModel
import com.thecocktailapp.ui.R
import com.thecocktailapp.ui.databinding.FragmentCocktailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailFragment :
    BaseFragment<CocktailViewState, CocktailIntent, FragmentCocktailBinding, CocktailViewModel>(
        layout = R.layout.fragment_cocktail
    ) {

    override val viewModel: CocktailViewModel by viewModels()
    override val binding by viewBinding(FragmentCocktailBinding::bind)

    override fun onPause() {
        super.onPause()
        emitAction(CommonIntent.Idle)
    }

    override fun onStart() {
        super.onStart()
        emitAction(CommonIntent.Init())
    }

    override fun renderView(state: CocktailViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.Initialized -> {
                initView()
            }

            is CocktailViewState.ShowError -> {

            }

            is CocktailViewState.ShowProgressDialog -> {
                showProgressDialog()
            }
        }
    }

    private fun initView() {
        setUpToolbar()
    }

    private fun setUpToolbar() {
        CoreToolbarMenuBinding.bind(binding.toolbarMenu).run {
            setUpMenuToolbar(
                allowGoBack = false,
                drawerLayout = (activity as KotlinActivity).drawerLayout,
                scrimColorId = R.color.color_scrim,
                titleColorId = R.color.color_on_primary,
                titleTextId = R.string.app_name,
                toolbar = toolbar,
                toolbarBackgroundColorId = R.color.color_primary,
                toolbarTitle = toolbarTitle
            )
        }
    }

}