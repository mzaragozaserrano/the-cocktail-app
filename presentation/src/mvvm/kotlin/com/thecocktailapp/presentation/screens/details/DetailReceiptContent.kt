package com.thecocktailapp.presentation.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.buttons.DroppedButton
import com.thecocktailapp.presentation.R

@Composable
fun DetailReceiptContent(modifier: Modifier = Modifier, instructions: String) {
    DroppedButton(
        modifier = modifier,
        buttonContentColor = MaterialTheme.colorScheme.primary,
        text = stringResource(id = R.string.show_steps_button),
        textStyle = MaterialTheme.typography.labelMedium,
        invisibleContent = {
            Column(
                modifier = Modifier.padding(bottom = 16.dp),
                content = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineSmall,
                        text = stringResource(id = R.string.title_instructions)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 20,
                        style = MaterialTheme.typography.bodyLarge,
                        text = instructions
                    )

                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun DetailReceiptContentPrev() {
    DetailReceiptContent(instructions = "This are the receipt steps")
}