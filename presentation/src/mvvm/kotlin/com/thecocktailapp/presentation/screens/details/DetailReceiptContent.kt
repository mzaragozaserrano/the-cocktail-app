package com.thecocktailapp.presentation.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.buttons.FallButton
import com.thecocktailapp.presentation.R

@Composable
fun DetailReceiptContent(modifier: Modifier = Modifier, instructions: String) {
    FallButton(
        modifier = modifier.padding(bottom = 16.dp, end = 16.dp, start = 16.dp),
        buttonContentColor = MaterialTheme.colorScheme.secondary,
        text = stringResource(id = R.string.show_steps_button),
        textStyle = MaterialTheme.typography.bodyMedium,
        invisibleContent = {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    color = colorResource(id = R.color.color_secondary_text_highlight),
                    text = stringResource(id = R.string.title_instructions)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 20,
                    text = instructions
                )

            }
        }
    )
}