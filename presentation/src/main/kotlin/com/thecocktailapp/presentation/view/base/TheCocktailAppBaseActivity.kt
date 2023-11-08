package com.thecocktailapp.presentation.view.base

import androidx.viewbinding.ViewBinding
import com.mzaragozaserrano.presentation.view.base.BaseActivity
import com.mzaragozaserrano.presentation.view.base.BaseViewModel
import com.thecocktailapp.ui.R

abstract class TheCocktailAppBaseActivity<S, I, VB : ViewBinding, VM : BaseViewModel<S, I>> :
    BaseActivity<S, I, VB, VM>() {

    override var loadingRaw: Int? = R.raw.loading

}