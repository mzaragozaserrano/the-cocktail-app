package com.thecocktailapp.presentation.compose.alerts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.compose.components.texts.LargeBoldText
import com.mzaragozaserrano.presentation.compose.components.texts.NormalMediumText
import com.thecocktailapp.presentation.common.vo.ErrorVO
import com.thecocktailapp.presentation.compose.buttons.AnimatedPushedButton
import com.thecocktailapp.ui.R

@Composable
fun ErrorAlert(error: ErrorVO, onRetryClicked: () -> Unit) {
    AlertDialog(
        confirmButton = {
            AnimatedPushedButton(
                buttonBackgroundColor = MaterialTheme.colorScheme.errorContainer,
                textColor = MaterialTheme.colorScheme.background,
                textId = R.string.retry_button
            ) {
                onRetryClicked()
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        title = {
            LargeBoldText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = colorResource(id = R.color.color_error_container),
                text = stringResource(R.string.title_error).uppercase()
            )
        },
        text = {
            NormalMediumText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = colorResource(id = R.color.color_on_background),
                maxLines = 5,
                text = stringResource(error.idMessage),
            )
        },
        onDismissRequest = { /*TODO*/ }
    )
}

@Preview
@Composable
private fun ErrorAlertPrev() {
    ErrorAlert(error = ErrorVO.Connectivity) {
        //Here will go the action when clicking on the button
    }
}