package com.thecocktailapp.presentation.view.base

import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import com.mzaragozaserrano.presentation.view.base.BaseActivity
import com.mzaragozaserrano.presentation.view.base.MVIViewModel
import com.thecocktailapp.presentation.compose.alerts.ErrorAlertFragment
import com.thecocktailapp.ui.R

abstract class TheCocktailAppBaseActivity<S, I, VB : ViewBinding, VM : MVIViewModel<S, I>> :
    BaseActivity<S, I, VB, VM>() {

    override var loadingRaw: Int? = R.raw.loading

    fun showErrorAlert(@StringRes idMessage: Int, onRetryButtonClicked: () -> Unit) {
        val errorAlertFragment =
            ErrorAlertFragment(idMessage = idMessage, onRetryButtonClicked = onRetryButtonClicked)
        errorAlertFragment.isCancelable = false
        supportFragmentManager.let { fragment ->
            errorAlertFragment.show(fragment, ErrorAlertFragment.ERROR_ALERT_TAG)
        }
    }

}