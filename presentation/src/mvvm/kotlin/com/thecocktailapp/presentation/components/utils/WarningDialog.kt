package com.thecocktailapp.presentation.components.utils

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thecocktailapp.core.presentation.compose.components.alerts.CardAlert
import com.thecocktailapp.presentation.R

@Composable
fun WarningDialog(
    modifier: Modifier = Modifier,
    @StringRes buttonTextId: Int,
    @StringRes messageTextId: Int,
    onButtonClicked: () -> Unit,
) {
    CardAlert(
        modifier = modifier,
        alertBackgroundColor = MaterialTheme.colorScheme.background,
        buttonBackgroundColor = MaterialTheme.colorScheme.primary,
        buttonTextColor = MaterialTheme.colorScheme.background,
        buttonTextId = buttonTextId,
        messageTextColor = colorResource(id = R.color.color_on_primary),
        messageTextId = messageTextId,
        titleTextColor = colorResource(id = R.color.color_primary),
        titleTextId = R.string.title_warning
    ) {
        onButtonClicked()
    }
}