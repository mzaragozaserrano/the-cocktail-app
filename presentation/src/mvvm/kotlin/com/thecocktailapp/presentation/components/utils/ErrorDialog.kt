package com.thecocktailapp.presentation.components.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.mzs.core.presentation.components.compose.alerts.CardAlert
import com.thecocktailapp.presentation.R

@Composable
fun ErrorDialog(
    buttonText: String,
    durationMillisBlockingButton: Int? = 3000,
    messageText: String,
    onButtonClicked: () -> Unit,
) {
    CardAlert(
        alertBackgroundColor = MaterialTheme.colorScheme.errorContainer,
        buttonBackgroundColor = MaterialTheme.colorScheme.error,
        buttonStyle = MaterialTheme.typography.labelMedium,
        buttonTextColor = MaterialTheme.colorScheme.onError,
        buttonText = buttonText,
        durationMillisBlockingButton = durationMillisBlockingButton,
        messageStyle = MaterialTheme.typography.bodyLarge,
        messageTextColor = MaterialTheme.colorScheme.onErrorContainer,
        messageText = messageText,
        titleStyle = MaterialTheme.typography.titleLarge,
        titleTextColor = MaterialTheme.colorScheme.onErrorContainer,
        titleText = stringResource(id = R.string.title_error),
        onButtonClicked = onButtonClicked
    )
}

@PreviewLightDark
@Composable
private fun ErrorDialogPrev() {
    ErrorDialog(
        buttonText = "Reintentar",
        messageText = "Compruebe su conexión a internet",
        onButtonClicked = { /*Here will go the action when clicking on the button*/ }
    )
}