package com.thecocktailapp.presentation.components.utils

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.mzs.core.presentation.components.compose.alerts.CardAlert
import com.thecocktailapp.presentation.R

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
        buttonText = stringResource(id = buttonTextId),
        messageStyle = MaterialTheme.typography.bodyMedium,
        messageTextColor = colorResource(id = R.color.color_on_background),
        messageText = stringResource(id = messageTextId),
        titleStyle = MaterialTheme.typography.titleMedium,
        titleTextColor = colorResource(id = R.color.color_error_container),
        titleText = stringResource(id = R.string.title_error)
    ) {
        onButtonClicked()
    }
}