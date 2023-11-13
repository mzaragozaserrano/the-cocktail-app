package com.thecocktailapp.presentation.view.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mzaragozaserrano.presentation.view.base.BaseFragment
import com.mzaragozaserrano.presentation.view.utils.extensions.hideProgressDialog
import com.mzaragozaserrano.presentation.view.utils.extensions.loadImageFromUrl
import com.mzaragozaserrano.presentation.view.utils.extensions.showProgressDialog
import com.mzaragozaserrano.presentation.view.utils.viewBinding.viewBinding
import com.thecocktailapp.presentation.common.utils.extensions.serializable
import com.thecocktailapp.presentation.common.utils.extensions.showErrorAlert
import com.thecocktailapp.presentation.view.utils.mvi.CommonIntent
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.DetailDrinkIntent
import com.thecocktailapp.presentation.view.utils.mvi.DetailDrinkViewState
import com.thecocktailapp.presentation.view.viewmodels.DetailDrinkViewModel
import com.thecocktailapp.presentation.view.vo.DrinkVO
import com.thecocktailapp.ui.R
import com.thecocktailapp.ui.databinding.FragmentDetailDrinkBinding
import com.thecocktailapp.ui.databinding.ItemIngredientBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDrinkFragment :
    BaseFragment<DetailDrinkViewState, DetailDrinkIntent, FragmentDetailDrinkBinding, DetailDrinkViewModel>(
        layout = R.layout.fragment_detail_drink
    ) {

    companion object {
        const val DRINK_ID = "DetailDrinkFragment.DRINK_ID"
    }

    override val viewModel: DetailDrinkViewModel by viewModels()
    override val binding by viewBinding(FragmentDetailDrinkBinding::bind)

    override fun onPause() {
        super.onPause()
        emitAction(CommonIntent.Idle)
    }

    override fun onStart() {
        super.onStart()
        emitAction(CommonIntent.Init())
    }

    override fun onBackPressed() {
        findNavController().navigate(R.id.action_DetailDrinkFragment_to_CocktailFragment)
    }

    override fun renderView(state: DetailDrinkViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.Initialized -> {
                getDrinkById()
            }

            is DetailDrinkViewState.ShowError -> {
                emitAction(CommonIntent.Idle)
                showErrorAlert(state.idMessage) {
                    getDrinkById()
                }
            }

            is DetailDrinkViewState.SetDrink -> {
                setUpDrink(state.drink)
            }

            is DetailDrinkViewState.ShowProgressDialog -> {
                showProgressDialog()
            }

        }
    }

    private fun getDrinkById() {
        emitAction(DetailDrinkIntent.GetDrinkById(arguments?.serializable(DRINK_ID) ?: 0))
    }

    private fun setUpDrink(drink: DrinkVO) {
        hideProgressDialog()
        binding.bind(drink)
        emitAction(CommonIntent.Idle)
    }

    private fun FragmentDetailDrinkBinding.bind(drink: DrinkVO) {
        apply {
            drinkName.text = drink.name
            drinkImage.loadImageFromUrl(
                placeHolderId = R.drawable.loading_img,
                url = drink.urlImage
            )
            drink.ingredients.forEach { ingredient ->
                ItemIngredientBinding.inflate(layoutInflater).also { binding ->
                    binding.ingredient.text = ingredient
                    listIngredients.addView(binding.root)
                }
            }
        }
    }

}