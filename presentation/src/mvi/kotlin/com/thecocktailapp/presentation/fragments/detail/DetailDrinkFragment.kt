package com.thecocktailapp.presentation.fragments.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonIntent
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.CommonViewState
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.DetailDrinkIntent
import com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi.DetailDrinkViewState
import com.thecocktailapp.core.presentation.view.base.BaseFragment
import com.thecocktailapp.core.presentation.view.utils.extensions.hideProgressDialog
import com.thecocktailapp.core.presentation.view.utils.extensions.loadImageFromUrl
import com.thecocktailapp.core.presentation.view.utils.extensions.showProgressDialog
import com.thecocktailapp.core.presentation.view.utils.viewBinding.viewBinding
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.databinding.FragmentDetailDrinkBinding
import com.thecocktailapp.presentation.databinding.ItemIngredientBinding
import com.thecocktailapp.presentation.viewmodels.DetailDrinkViewModel
import com.thecocktailapp.presentation.vo.DrinkVO
import dagger.hilt.android.AndroidEntryPoint
import presentation.utils.serializable

@AndroidEntryPoint
class DetailDrinkFragment :
    BaseFragment<DetailDrinkViewState, DetailDrinkIntent, FragmentDetailDrinkBinding, DetailDrinkViewModel>(
        R.layout.fragment_detail_drink
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
        findNavController().navigate(R.id.action_DetailDrinkFragment_to_HomeFragment)
    }

    override fun renderView(state: DetailDrinkViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.Initialized -> {
                getDrinkById()
            }

            is DetailDrinkViewState.ShowError -> {
                emitAction(CommonIntent.Idle)
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