package com.thecocktailapp.presentation.view.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mzaragozaserrano.presentation.view.utils.extensions.hideProgressDialog
import com.mzaragozaserrano.presentation.view.utils.extensions.showProgressDialog
import com.mzaragozaserrano.presentation.view.utils.viewBinding.viewBinding
import com.thecocktailapp.presentation.view.base.TheCocktailAppBaseFragment
import com.thecocktailapp.presentation.view.utils.extensions.loadImageFromUrl
import com.thecocktailapp.presentation.view.utils.mvi.CommonIntent
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.SplashIntent
import com.thecocktailapp.presentation.view.utils.mvi.SplashViewState
import com.thecocktailapp.presentation.view.viewmodels.SplashViewModel
import com.thecocktailapp.presentation.view.vo.DrinkVO
import com.thecocktailapp.ui.R
import com.thecocktailapp.ui.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment :
    TheCocktailAppBaseFragment<SplashViewState, SplashIntent, FragmentSplashBinding, SplashViewModel>(
        layout = R.layout.fragment_splash, allowGoBack = true
    ) {

    override val viewModel: SplashViewModel by viewModels()
    override val binding by viewBinding(FragmentSplashBinding::bind)

    override fun onPause() {
        super.onPause()
        emitAction(CommonIntent.Idle)
    }

    override fun onStart() {
        super.onStart()
        emitAction(CommonIntent.Init())
    }

    override fun FragmentSplashBinding.setUpListeners() {
        seeButton.apply { setOnClickListener { emitAction(SplashIntent.GoToDrinkDetail) } }
        cancelButton.apply { setOnClickListener { emitAction(SplashIntent.GoToMain) } }
    }

    override fun renderView(state: SplashViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.Initialized -> {
                getRandomDrink()
            }

            is SplashViewState.Navigate.ToDrinkDetail -> {

            }

            is SplashViewState.Navigate.ToMain -> {
                findNavController().navigate(R.id.action_SplashFragment_to_CocktailFragment)
            }

            is SplashViewState.ShowError -> {

            }

            is SplashViewState.ShowProgressDialog -> {
                showProgressDialog()
            }

            is SplashViewState.SetDailyDrink -> {
                setUpDailyDrink(state)
            }
        }
    }

    private fun getRandomDrink() {
        emitAction(SplashIntent.GetRandomDrink)
    }

    private fun setUpDailyDrink(state: SplashViewState.SetDailyDrink) {
        hideProgressDialog()
        binding.bindDailyDrink(state.drink)
        emitAction(CommonIntent.Idle)
    }

    private fun FragmentSplashBinding.bindDailyDrink(drink: DrinkVO) {
        apply {
            cocktailImage.loadImageFromUrl(
                url = drink.urlImage,
                onSuccess = {
                    cocktailName.text = drink.name
                    groupTexts.visibility = View.VISIBLE
                    groupButtons.visibility = View.VISIBLE
                }
            )
        }
    }

}