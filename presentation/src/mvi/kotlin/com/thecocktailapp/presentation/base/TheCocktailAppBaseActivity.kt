package com.thecocktailapp.presentation.base

import androidx.viewbinding.ViewBinding
import presentation.base.BaseActivity
import presentation.base.MVIViewModel
import com.thecocktailapp.presentation.R

abstract class TheCocktailAppBaseActivity<S, I, VB : ViewBinding, VM : MVIViewModel<S, I>> :
    BaseActivity<S, I, VB, VM>() {

    override var loadingRaw: Int? = R.raw.loading

}