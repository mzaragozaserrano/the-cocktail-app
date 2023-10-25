package com.thecocktailapp.presentation.compose.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColorScheme = lightColorScheme(
    primary = GreenPastel,
    onPrimary = DarkGrayGreen,
    primaryContainer = GreenPastel,
    onPrimaryContainer = GrayMedium,
    secondary = BeigePastel,
    onSecondary = LightGray,
    secondaryContainer = BeigePastelLight,
    onSecondaryContainer = GrayMedium,
    tertiary = BluePastel,
    onTertiary = DarkGrayGreen,
    tertiaryContainer = BluePastelLight,
    onTertiaryContainer = DarkGray,
    error = RedPastel,
    errorContainer = RedPastelDark,
    onError = LightGray,
    onErrorContainer = RedPastelLight,
    background = White,
    onBackground = DarkGray,
    surface = White,
    onSurface = DarkGray,
    surfaceVariant = GrayLight,
    onSurfaceVariant = DarkGray,
    outline = GrayLight,
    inverseOnSurface = GrayLight,
    inverseSurface = DarkGray,
    inversePrimary = GreenPastel,
    surfaceTint = GreenPastel,
    outlineVariant = GrayMedium,
    scrim = Black,
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkGrayGreen,
    onPrimary = White,
    primaryContainer = DarkGrayGreen,
    onPrimaryContainer = White,
    secondary = LightGray,
    onSecondary = White,
    secondaryContainer = GrayMedium,
    onSecondaryContainer = White,
    tertiary = GrayLight,
    onTertiary = White,
    tertiaryContainer = BluePastel,
    onTertiaryContainer = White,
    error = RedPastel,
    errorContainer = RedPastelDark,
    onError = White,
    onErrorContainer = RedPastelDark,
    background = DarkGray,
    onBackground = White,
    surface = LightGray,
    onSurface = White,
    surfaceVariant = GrayMedium,
    onSurfaceVariant = White,
    outline = GrayLight,
    inverseOnSurface = DarkGray,
    inverseSurface = White,
    inversePrimary = GreenPastel,
    surfaceTint = DarkGrayGreen,
    outlineVariant = GrayMedium,
    scrim = Black,
)

@Composable
fun TheCocktailAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {

    /*class CustomFontProvider : FontProvider {
        override val fontFamily: FontFamily =
            FontFamily(Font(R.font.nosifer_regular, FontWeight.Normal))
    }

    CustomTextModule.fontConfiguration.font.value = CustomFontProvider().fontFamily*/

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}