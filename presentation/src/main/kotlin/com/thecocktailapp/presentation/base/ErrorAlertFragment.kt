package com.thecocktailapp.presentation.base

import androidx.annotation.StringRes
import com.mzaragozaserrano.presentation.view.base.BaseDialogFragment
import com.thecocktailapp.ui.databinding.LayoutErrorAlertBinding

class ErrorAlertFragment(
    @StringRes private val idMessage: Int,
    private val onRetryButtonClicked: () -> Unit,
) : BaseDialogFragment<LayoutErrorAlertBinding>() {

    companion object {
        const val ERROR_ALERT_TAG = "ErrorAlert.TAG"
    }

    override val binding by lazy { LayoutErrorAlertBinding.inflate(layoutInflater) }

    override fun LayoutErrorAlertBinding.setUpView() {

    }

}