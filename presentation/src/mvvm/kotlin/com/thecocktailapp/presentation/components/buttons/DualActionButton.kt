package com.thecocktailapp.presentation.components.buttons

import android.view.MotionEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.buttons.PushedButton
import com.thecocktailapp.presentation.utils.SPLASH_CANCEL_BUTTON
import com.thecocktailapp.presentation.utils.SPLASH_SEE_BUTTON

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DualActionButton(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    buttonText: String,
    text: String,
    onPositiveButtonClicked: () -> Unit,
    onNegativeButtonClicked: () -> Unit,
) {

    var buttonSize by remember { mutableStateOf(value = IntSize.Zero) }
    var isClickAvailable by remember { mutableStateOf(value = true) }
    var isLaunchingAction by remember { mutableStateOf(value = false) }
    var isPressed by remember { mutableStateOf(value = false) }
    val buttonSecondaryTextColor =
        if (isPressed) buttonBackgroundColor.copy(alpha = 0.66f) else buttonBackgroundColor

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        PushedButton(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(tag = SPLASH_SEE_BUTTON),
            buttonBackgroundColor = buttonBackgroundColor,
            text = buttonText,
            textColor = buttonTextColor,
            textStyle = MaterialTheme.typography.titleSmall,
            onButtonClicked = onPositiveButtonClicked
        )
        Text(
            modifier = Modifier
                .pointerInteropFilter { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_CANCEL -> {
                            isPressed = false
                        }
                        MotionEvent.ACTION_DOWN -> {
                            isPressed = true
                        }

                        MotionEvent.ACTION_MOVE -> {
                            val x = motionEvent.x.toInt()
                            val y = motionEvent.y.toInt()
                            isPressed =
                                x in 0 until buttonSize.width && y in 0 until buttonSize.height
                            isClickAvailable = isPressed
                        }

                        MotionEvent.ACTION_UP -> {
                            if (isClickAvailable) {
                                isPressed = false
                                isLaunchingAction = true
                            } else {
                                isClickAvailable = true
                            }
                        }
                    }
                    true
                }
                .onGloballyPositioned { layoutCoordinates ->
                    buttonSize = layoutCoordinates.size
                }
                .testTag(tag = SPLASH_CANCEL_BUTTON),
            color = buttonSecondaryTextColor,
            text = text,
            textAlign = TextAlign.Center
        )
    }

}

@Preview
@Composable
private fun DualActionButtonPrev() {
    DualActionButton(
        buttonBackgroundColor = MaterialTheme.colorScheme.primary,
        buttonText = "Aceptar",
        buttonTextColor = MaterialTheme.colorScheme.onPrimary,
        text = "Cancelar",
        onPositiveButtonClicked = { /*Here will go the action when clicking on the main button*/ },
        onNegativeButtonClicked = { /*Here will go the action when clicking on the secondary button*/ }
    )
}