package com.thecocktailapp.presentation.screens.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.compose.components.buttons.FallButton
import com.mzaragozaserrano.presentation.compose.components.texts.LargeBoldText
import com.mzaragozaserrano.presentation.compose.components.texts.SmallMediumText
import com.thecocktailapp.ui.R

@Composable
fun DetailInstructionsContent(modifier: Modifier = Modifier, instructions: String) {

    var isPressed by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }

    val offset by animateDpAsState(
        animationSpec = tween(durationMillis = 300),
        targetValue = if (isPressed) 25.dp else 0.dp,
        label = ""
    )

    AnimatedContent(
        targetState = isVisible,
        label = ""
    ) { show ->
        if (show) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                LargeBoldText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    color = colorResource(id = R.color.color_secondary_text_highlight),
                    text = stringResource(id = R.string.title_instructions)
                )
                SmallMediumText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 20,
                    text = instructions
                )
            }
        }
    }

    AnimatedContent(
        modifier = modifier.offset(y = offset),
        targetState = !isVisible,
        label = ""
    ) { show ->
        if (show) {
            FallButton(
                modifier = modifier,
                iconColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.primary,
                textId = R.string.show_steps_button,
                onButtonClicked = {
                    isVisible = true
                },
                onButtonPressed = {
                    isPressed = true
                }
            )
        }
    }

}