package com.thecocktailapp.presentation.compose.buttons

import android.view.MotionEvent
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.compose.components.texts.SmallMediumText
import com.thecocktailapp.ui.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AnimatedPushedButton(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color = Color.LightGray,
    textColor: Color = Color.Black,
    @StringRes textId: Int,
    onButtonClicked: () -> Unit,
) {

    var isPressed by remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (isPressed) 0.93f else 1f, label = "")

    Card(
        modifier = modifier
            .scale(scale = scale.value)
            .clip(shape = RoundedCornerShape(8.dp))
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        isPressed = true
                    }

                    MotionEvent.ACTION_UP -> {
                        isPressed = false
                        onButtonClicked()
                    }
                }
                true
            },
        colors = CardDefaults.cardColors(containerColor = buttonBackgroundColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        SmallMediumText(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .align(CenterHorizontally),
            color = textColor,
            text = stringResource(id = textId).uppercase(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PushedButtonPrev() {
    AnimatedPushedButton(textId = R.string.see_button) {
        //Here will go the action when clicking on the button
    }
}