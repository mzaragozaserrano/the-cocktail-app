package com.thecocktailapp.presentation.fragments

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.mzs.core.presentation.base.CoreBaseFragment
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
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailDrinkFragment :
    CoreBaseFragment<DetailDrinkViewState, DetailDrinkIntent, DetailDrinkAction, DetailDrinkResult, FragmentDetailDrinkBinding, DetailDrinkViewModel>(
        R.layout.fragment_detail_drink
    ) {

    companion object {
        const val DRINK_ID = "DetailDrinkFragment.DRINK_ID"
    }

    override val viewModel: DetailDrinkViewModel by viewModel()
    override val binding by viewBinding(FragmentDetailDrinkBinding::bind)

    override fun onBackPressed() {
        findNavController().navigate(R.id.action_DetailDrinkFragment_to_HomeFragment)
    }

    override fun onPause() {
        super.onPause()
        emitAction(intent = CommonIntent.Idle)
    }

    override fun onStart() {
        super.onStart()
        emitAction(intent = CommonIntent.Init())
    }

    override fun renderView(state: DetailDrinkViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.Initialized -> {
                getDrinkById()
            }

            is DetailDrinkViewState.ShowError -> {
                emitAction(intent = CommonIntent.Idle)
            }

            is DetailDrinkViewState.ShowView -> {
                setUpView(drink = state.drink)
            }

            is DetailDrinkViewState.ShowProgressDialog -> {
                showProgressDialog()
            }

        }
    }

    private fun getDrinkById() {
        emitAction(intent = DetailDrinkIntent.GetDrinkById(id = arguments?.getInt(DRINK_ID)))
    }

    private fun setUpView(drink: DrinkVO) {
        hideProgressDialog()
        binding.bind(drink = drink)
        emitAction(intent = CommonIntent.Idle)
    }

    private fun FragmentDetailDrinkBinding.bind(drink: DrinkVO) {
        apply {
            drinkImage.loadImageFromUrl(
                placeHolderId = R.drawable.loading_img,
                url = drink.urlImage
            )
            if (drink.glass.isNotEmpty()) {
                glassLabel.apply {
                    val buttonBackground = buttonBackground.background as GradientDrawable
                    buttonBackground.setColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.secondary_container
                        )
                    )
                    val iconBackground = iconBackground.background as GradientDrawable
                    iconBackground.setColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.secondary
                        )
                    )
                    labelIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_cocktail
                        )
                    )
                    labelName.text = drink.glass
                    root.visibility = View.VISIBLE
                }
            }
            /*drinkType?.let { type ->
                WavyLabel(
                    buttonBackgroundColor = colorResource(id = type.buttonBackgroundColorId),
                    iconBackgroundColor = colorResource(id = type.iconBackgroundColorId),
                    iconId = type.iconId,
                    iconTint = colorResource(id = type.iconTintId),
                    textColor = colorResource(id = type.textColorId),
                    text = stringResource(id = type.labelId),
                    textStyle = MaterialTheme.typography.labelLarge
                )
            }
            if (glass.isNotEmpty()) {
                WavyLabel(
                    buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                    iconBackgroundColor = MaterialTheme.colorScheme.secondary,
                    iconId = R.drawable.ic_cocktail,
                    iconTint = MaterialTheme.colorScheme.onSecondary,
                    textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    text = glass,
                    textStyle = MaterialTheme.typography.labelLarge
                )
            }*/
            drinkName.text = drink.name
            drink.ingredients.forEach { ingredient ->
                ItemIngredientBinding.inflate(layoutInflater).also { binding ->
                    binding.ingredient.text = ingredient
                    listIngredients.addView(binding.root)
                }
            }
        }
    }

}