package com.thecocktailapp.presentation.vo

import androidx.annotation.StringRes

data class ErrorAlertVO(@StringRes val idMessage: Int, val onRetryButtonClicked: () -> Unit)