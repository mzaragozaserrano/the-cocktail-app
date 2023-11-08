package com.thecocktailapp.presentation.view.base

import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.mzaragozaserrano.presentation.view.base.BaseFragment
import com.mzaragozaserrano.presentation.view.base.BaseViewModel

abstract class TheCocktailAppBaseFragment<S, I, VB : ViewBinding, VM : BaseViewModel<S, I>>(
    @LayoutRes override val layout: Int,
    private val allowGoBack: Boolean,
) : BaseFragment<S, I, VB, VM>(layout) {

    override fun onBackPressed() {
        if (allowGoBack) {
            findNavController().popBackStack()
        }
    }

}