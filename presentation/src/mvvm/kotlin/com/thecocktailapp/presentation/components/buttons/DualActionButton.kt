package com.thecocktailapp.presentation.components.buttons

import android.view.MotionEvent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.presentation.compose.components.buttons.PushedButton
import com.thecocktailapp.core.presentation.compose.components.texts.ExtraSmallMediumText

@OptIn(ExperimentalComposeUiApi::class)
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

    var isPressed by remember { mutableStateOf(value = false) }
    val buttonSecondaryTextColor =
        if (isPressed) buttonBackgroundColor.copy(alpha = 0.66f) else buttonBackgroundColor

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        PushedButton(
            modifier = Modifier.fillMaxWidth(),
            buttonBackgroundColor = buttonBackgroundColor,
            textColor = buttonTextColor,
            textId = buttonTextId,
            textPaddingVertical = 12.dp
        ) {
            onSeeClicked()
        }
        ExtraSmallMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_CANCEL -> {
                            isPressed = false
                        }

                        MotionEvent.ACTION_DOWN -> {
                            isPressed = true
                            onCancelClicked()
                        }

                        MotionEvent.ACTION_UP -> {
                            isPressed = false
                        }
                    }
                    true
                },
            color = buttonSecondaryTextColor,
            text = stringResource(id = textId),
            textAlign = TextAlign.Center
        )
    }

}