package com.thecocktailapp.presentation.base

import androidx.viewbinding.ViewBinding
import com.mzaragozaserrano.presentation.view.base.BaseActivity
import com.mzaragozaserrano.presentation.view.base.MVIViewModel
import com.thecocktailapp.ui.R

abstract class TheCocktailAppBaseActivity<S, I, VB : ViewBinding, VM : MVIViewModel<S, I>> :
    BaseActivity<S, I, VB, VM>() {

    override var loadingRaw: Int? = R.raw.loading

}