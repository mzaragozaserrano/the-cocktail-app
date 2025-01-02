/*
package com.thecocktailapp.presentation.screens.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.buttons.FallButton
import com.thecocktailapp.presentation.R

@Composable
fun DetailInstructionsContent(
    modifier: Modifier = Modifier,
    instructions: String,
    onButtonClicked: () -> Unit
) {

    val offset by animateDpAsState(
        animationSpec = tween(durationMillis = 300),
        targetValue = if (isPressed) 25.dp else 0.dp,
        label = emptyText
    )

    AnimatedContent(
        targetState = isVisible,
        label = emptyText
    ) { show ->
        if (show) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
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
    }
    AnimatedContent(
        modifier = modifier.offset { IntOffset(0, offset.roundToPx()) },
        targetState = !isVisible,
        label = emptyText
    ) { show ->
        if (show) {
            FallButton(
                modifier = modifier.padding(horizontal = 16.dp),
                iconColor = MaterialTheme.colorScheme.primary,
                text = stringResource(id = R.string.show_steps_button),
                textColor = MaterialTheme.colorScheme.primary,
                textStyle = MaterialTheme.typography.bodyMedium,
                onButtonClicked = onButtonClicked
            )
        }
    }

}*/
