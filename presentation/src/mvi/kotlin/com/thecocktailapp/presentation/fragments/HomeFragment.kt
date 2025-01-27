package com.thecocktailapp.presentation.fragments

import com.mzs.core.databinding.CoreToolbarMenuBinding
import com.mzs.core.presentation.base.CoreBaseFragment
import com.mzs.core.presentation.utils.extensions.showProgressDialog
import com.mzs.core.presentation.utils.viewBinding.viewBinding
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.activities.KotlinActivity
import com.thecocktailapp.presentation.databinding.FragmentHomeBinding
import com.thecocktailapp.presentation.utils.CommonIntent
import com.thecocktailapp.presentation.utils.CommonViewState
import com.thecocktailapp.presentation.utils.HomeAction
import com.thecocktailapp.presentation.utils.HomeIntent
import com.thecocktailapp.presentation.utils.HomeResult
import com.thecocktailapp.presentation.utils.HomeViewState
import com.thecocktailapp.presentation.utils.setUpMenuToolbar
import com.thecocktailapp.presentation.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    CoreBaseFragment<HomeViewState, HomeIntent, HomeAction, HomeResult, FragmentHomeBinding, HomeViewModel>(
        R.layout.fragment_home
    ) {

    override val viewModel: HomeViewModel by viewModel()
    override val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onPause() {
        super.onPause()
        emitAction(intent = CommonIntent.Idle)
    }

    override fun onStart() {
        super.onStart()
        emitAction(intent = CommonIntent.Init())
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