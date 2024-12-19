package com.thecocktailapp.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mzs.core.presentation.base.CoreBaseFragment
import com.mzs.core.presentation.utils.extensions.getSerializableExtra
import com.mzs.core.presentation.utils.extensions.hideProgressDialog
import com.mzs.core.presentation.utils.extensions.loadImageFromUrl
import com.mzs.core.presentation.utils.extensions.showProgressDialog
import com.mzs.core.presentation.utils.viewBinding.viewBinding
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.databinding.FragmentDetailDrinkBinding
import com.thecocktailapp.presentation.databinding.ItemIngredientBinding
import com.thecocktailapp.presentation.utils.CommonIntent
import com.thecocktailapp.presentation.utils.CommonViewState
import com.thecocktailapp.presentation.utils.DetailDrinkAction
import com.thecocktailapp.presentation.utils.DetailDrinkIntent
import com.thecocktailapp.presentation.utils.DetailDrinkResult
import com.thecocktailapp.presentation.utils.DetailDrinkViewState
import com.thecocktailapp.presentation.viewmodels.DetailDrinkViewModel
import com.thecocktailapp.presentation.vo.DrinkVO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDrinkFragment :
    CoreBaseFragment<DetailDrinkViewState, DetailDrinkIntent, DetailDrinkAction, DetailDrinkResult, FragmentDetailDrinkBinding, DetailDrinkViewModel>(
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
        emitAction(DetailDrinkIntent.GetDrinkById(getSerializableExtra(DRINK_ID, Int::class.java)))
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