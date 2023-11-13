package com.thecocktailapp.presentation.view.fragments

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mzaragozaserrano.presentation.view.base.BaseFragment
import com.mzaragozaserrano.presentation.view.utils.extensions.hideProgressDialog
import com.mzaragozaserrano.presentation.view.utils.extensions.loadImageFromUrl
import com.mzaragozaserrano.presentation.view.utils.extensions.showProgressDialog
import com.mzaragozaserrano.presentation.view.utils.viewBinding.viewBinding
import com.thecocktailapp.presentation.common.utils.extensions.showErrorAlert
import com.thecocktailapp.presentation.view.activities.HomeActivity
import com.thecocktailapp.presentation.view.base.TheCocktailAppBaseActivity
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
    BaseFragment<SplashViewState, SplashIntent, FragmentSplashBinding, SplashViewModel>(layout = R.layout.fragment_splash) {

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

    override fun onBackPressed() {
        super.onBackPressed()
        (activity as TheCocktailAppBaseActivity<*, *, *, *>).clearAndNavigateToNewActivity(
            HomeActivity::class.java
        )
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
                navigateToDetailDrinkFragment(state.id)
            }

            is SplashViewState.Navigate.ToCocktailFragment -> {
                navigateToCocktailFragment()
            }

            is SplashViewState.SetDrink -> {
                setUpDrink(state.drink)
            }

            is SplashViewState.ShowError -> {
                emitAction(CommonIntent.Idle)
                showErrorAlert(state.idMessage) {
                    getRandomDrink()
                }
            }

            is SplashViewState.ShowProgressDialog -> {
                showProgressDialog()
            }
        }
    }

    private fun navigateToDetailDrinkFragment(id: Int) {
        hideProgressDialog()
        findNavController().navigate(
            R.id.action_SplashFragment_to_DetailDrinkFragment,
            bundleOf(DetailDrinkFragment.DRINK_ID to id)
        )
    }

    private fun navigateToCocktailFragment() {
        hideProgressDialog()
        findNavController().navigate(R.id.action_SplashFragment_to_CocktailFragment)
    }

    private fun getRandomDrink() {
        emitAction(SplashIntent.GetRandomDrink)
    }

    private fun setUpDrink(drink: DrinkVO) {
        hideProgressDialog()
        binding.bind(drink)
        emitAction(CommonIntent.Idle)
    }

    private fun FragmentSplashBinding.bind(drink: DrinkVO) {
        apply {
            drinkImage.loadImageFromUrl(
                placeHolderId = R.drawable.loading_img,
                url = drink.urlImage
            )
            drinkName.text = drink.name
            groupTexts.visibility = View.VISIBLE
            groupButtons.visibility = View.VISIBLE
        }
    }

}