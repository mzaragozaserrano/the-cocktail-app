package com.thecocktailapp.presentation.view.fragments

import androidx.fragment.app.viewModels
import com.mzaragozaserrano.presentation.databinding.CoreToolbarMenuBinding
import com.mzaragozaserrano.presentation.view.utils.extensions.hideProgressDialog
import com.mzaragozaserrano.presentation.view.utils.extensions.setUpMenuToolbar
import com.mzaragozaserrano.presentation.view.utils.extensions.showProgressDialog
import com.mzaragozaserrano.presentation.view.utils.viewBinding.viewBinding
import com.thecocktailapp.presentation.view.activities.KotlinActivity
import com.thecocktailapp.presentation.view.base.TheCocktailAppBaseFragment
import com.thecocktailapp.presentation.view.utils.mvi.CocktailIntent
import com.thecocktailapp.presentation.view.utils.mvi.CocktailViewState
import com.thecocktailapp.presentation.view.utils.mvi.CommonIntent
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.viewmodels.CocktailViewModel
import com.thecocktailapp.ui.R
import com.thecocktailapp.ui.databinding.FragmentCocktailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailFragment :
    TheCocktailAppBaseFragment<CocktailViewState, CocktailIntent, FragmentCocktailBinding, CocktailViewModel>(
        layout = R.layout.fragment_cocktail, allowGoBack = true
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
            is CommonViewState.SetUpView -> {
                initView()
            }

            is CocktailViewState.ShowError -> {

            }

            is CocktailViewState.ShowProgressDialog -> {
                showProgressDialog()
            }

            is CocktailViewState.SetDailyCocktail -> {
                setUpDailyCocktail(state)
            }
        }
    }

    //TODO(El icono de la navegacion lo quiero en otro color, ver como se puede controlar los tamaños de las cosas)

    private fun initView() {
        setUpToolbar()
        getRandomCocktail()
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

    private fun getRandomCocktail() {
        emitAction(CocktailIntent.GetRandomCocktail)
    }

    private fun setUpDailyCocktail(state: CocktailViewState.SetDailyCocktail) {
        hideProgressDialog()
        binding.prueba.text = state.drink.urlImage
        emitAction(CommonIntent.Idle)
    }

}