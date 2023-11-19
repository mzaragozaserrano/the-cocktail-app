package com.thecocktailapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.compose.components.buttons.PushedButton
import com.mzaragozaserrano.presentation.compose.components.texts.ExtraSmallMediumText

@Composable
fun DualActionButton(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    @StringRes buttonTextId: Int,
    @StringRes textId: Int,
    onSeeClicked: () -> Unit,
    onCancelClicked: () -> Unit,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        PushedButton(
            modifier = Modifier.fillMaxWidth(),
            buttonBackgroundColor = buttonBackgroundColor,
            textColor = buttonTextColor,
            textId = buttonTextId
        ) {
            onSeeClicked()
        }
        ExtraSmallMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCancelClicked() },
            color = buttonBackgroundColor,
            text = stringResource(id = textId),
            textAlign = TextAlign.Center
        )
    }

}