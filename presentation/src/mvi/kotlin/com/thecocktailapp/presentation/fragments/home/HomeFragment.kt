package com.thecocktailapp.presentation.fragments.home

import androidx.fragment.app.viewModels
import com.thecocktailapp.core.databinding.CoreToolbarMenuBinding
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.activities.KotlinActivity
import com.thecocktailapp.presentation.databinding.FragmentHomeBinding
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonIntent
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonViewState
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeIntent
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.HomeViewState
import com.thecocktailapp.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import presentation.base.BaseFragment
import com.thecocktailapp.core.presentation.compose.utils.extensions.setUpMenuToolbar
import com.thecocktailapp.core.presentation.compose.utils.extensions.showProgressDialog
import com.thecocktailapp.core.presentation.compose.utils.viewBinding.viewBinding

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeViewState, HomeIntent, FragmentHomeBinding, HomeViewModel>(
        R.layout.fragment_home
    ) {

    override val viewModel: HomeViewModel by viewModels()
    override val binding by viewBinding(FragmentHomeBinding::bind)

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
                initView()
            }

            is HomeViewState.ShowError -> {

            }

            is HomeViewState.ShowProgressDialog -> {
                showProgressDialog()
            }
        }
    }

    private fun initView() {
        setUpToolbar()
    }

    private fun setUpToolbar() {
        CoreToolbarMenuBinding.bind(binding.toolbarMenu.root).run {
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