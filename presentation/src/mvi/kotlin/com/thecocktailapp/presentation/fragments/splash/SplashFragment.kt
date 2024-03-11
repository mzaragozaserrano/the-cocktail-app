package com.thecocktailapp.presentation.fragments.splash

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mzs.core.presentation.view.base.BaseFragment
import com.mzs.core.presentation.view.utils.extensions.hideProgressDialog
import com.mzs.core.presentation.view.utils.extensions.loadImageFromUrl
import com.mzs.core.presentation.view.utils.extensions.showProgressDialog
import com.mzs.core.presentation.view.utils.viewBinding.viewBinding
import com.thecocktailapp.presentation.fragments.detail.DetailDrinkFragment
import com.thecocktailapp.presentation.utils.mvi.CommonIntent
import com.thecocktailapp.presentation.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.utils.mvi.SplashIntent
import com.thecocktailapp.presentation.utils.mvi.SplashViewState
import com.thecocktailapp.presentation.viewmodels.SplashViewModel
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.ui.R
import com.thecocktailapp.ui.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment :
    BaseFragment<SplashViewState, SplashIntent, FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

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
        TODO("Cerrar app")
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

            is SplashViewState.Navigate.ToHomeFragment -> {
                navigateToHomeFragment()
            }

            is SplashViewState.SetDrink -> {
                setUpDrink(state.drink)
            }

            is SplashViewState.ShowError -> {
                emitAction(CommonIntent.Idle)
            }

            is SplashViewState.ShowProgressDialog -> {
                showProgressDialog()
            }
        }
    }

    private fun getRandomDrink() {
        emitAction(SplashIntent.GetRandomDrink)
    }

    private fun navigateToDetailDrinkFragment(id: Int) {
        hideProgressDialog()
        findNavController().navigate(
            R.id.action_SplashFragment_to_DetailDrinkFragment,
            bundleOf(DetailDrinkFragment.DRINK_ID to id)
        )
    }

    private fun navigateToHomeFragment() {
        hideProgressDialog()
        findNavController().navigate(R.id.action_SplashFragment_to_HomeFragment)
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