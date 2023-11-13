package com.thecocktailapp.presentation.common.utils.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.thecocktailapp.presentation.view.base.TheCocktailAppBaseActivity

fun Fragment.showErrorAlert(
    @StringRes idMessage: Int,
    onRetryButtonClicked: () -> Unit,
) {
    (activity as TheCocktailAppBaseActivity<*, *, *, *>).showErrorAlert(
        idMessage = idMessage,
        onRetryButtonClicked = onRetryButtonClicked
    )
}