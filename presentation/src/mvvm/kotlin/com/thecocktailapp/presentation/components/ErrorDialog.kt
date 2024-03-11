package com.thecocktailapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.thecocktailapp.presentation.R
import presentation.components.alerts.CardAlert

@Composable
fun ErrorDialog(
    @StringRes buttonTextId: Int,
    @StringRes messageTextId: Int,
    onButtonClicked: () -> Unit,
) {
    CardAlert(
        alertBackgroundColor = MaterialTheme.colorScheme.background,
        buttonBackgroundColor = MaterialTheme.colorScheme.errorContainer,
        buttonTextColor = MaterialTheme.colorScheme.background,
        buttonTextId = buttonTextId,
        messageTextColor = colorResource(id = R.color.color_on_background),
        messageTextId = messageTextId,
        titleTextColor = colorResource(id = R.color.color_error_container),
        titleTextId = R.string.title_error
    ) {
        onButtonClicked()
    }
}