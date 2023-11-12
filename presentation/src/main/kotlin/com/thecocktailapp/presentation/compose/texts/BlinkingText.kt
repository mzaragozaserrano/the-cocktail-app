package com.thecocktailapp.presentation.compose.texts

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mzaragozaserrano.presentation.compose.components.texts.ExtraLargeBlackText

@Composable
fun BlinkingText(modifier: Modifier = Modifier, color: Color = Color.Black, text: String) {
    val isBlinking by remember { mutableStateOf(true) }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val alpha by infiniteTransition.animateFloat(
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        initialValue = 1f,
        label = "",
        targetValue = if (isBlinking) 0.3f else 1f,
    )

    val scale by infiniteTransition.animateFloat(
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        initialValue = 1f,
        label = "",
        targetValue = if (isBlinking) 0.95f else 1f,
    )

    ExtraLargeBlackText(
        modifier = modifier
            .alpha(alpha)
            .scale(scale),
        color = color,
        text = text
    )

}

@Preview(showBackground = true)
@Composable
private fun PreviewBlinkingText() {
    BlinkingText(text = "¡Hola, Mundo!")
}