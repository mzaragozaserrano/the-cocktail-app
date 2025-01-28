package com.thecocktailapp.presentation.theme

import androidx.compose.ui.graphics.Color

object LightColorTokens {
    val Primary = Color(0xFF8C5E3C) // Warm brown
    val OnPrimary = Color(0xFFF5F1E1) // Off-white
    val PrimaryContainer = Color(0xFFD9C0A2) // Soft brown
    val OnPrimaryContainer = Color(0xFF5A3A23) // Darker brown
    val InversePrimary = Color(0xFFF5D3A4) // Light warm brown

    val Secondary = Color(0xFF4A5B42) // Olive green
    val OnSecondary = Color(0xFFF5F1E1) // Off-white
    val SecondaryContainer = Color(0xFF6B7A64) // Softer green
    val OnSecondaryContainer = Color(0xFF2D3A2A) // Darker green

    val Tertiary = Color(0xFF6C7A56) // Muted olive green
    val OnTertiary = Color(0xFFF5F1E1) // Off-white
    val TertiaryContainer = Color(0xFFA6B28C) // Lighter olive
    val OnTertiaryContainer = Color(0xFF2A3C27) // Darker green

    val Background = Color(0xFFF5F1E1) // Light neutral
    val OnBackground = Color(0xFF4A4A4A) // Dark gray
    val Surface = Color(0xFFF5F1E1) // Light neutral
    val OnSurface = Color(0xFF4A4A4A) // Dark gray
    val SurfaceVariant = Color(0xFFD1C9B7) // Subtle beige
    val OnSurfaceVariant = Color(0xFF7A6F58) // Muted olive brown
    val InverseSurface = Color(0xFFF5F5F5) // Light gray
    val InverseOnSurface = Color(0xFF3A3A3A) // Dark gray

    val SurfaceBright = Color(0xFFD9C0A2) // Soft brown for bright surfaces
    val SurfaceContainer = Color(0xFFE4D5B8) // Light beige for containers
    val SurfaceContainerHigh = Color(0xFFD1C9B7) // Slightly darker surface container
    val SurfaceContainerHighest = Color(0xFFC0B89F) // Higher surface container

    val Error = Color(0xFFB77D43) // Burnt orange
    val OnError = Color(0xFFFFFFFF) // White for error text
    val ErrorContainer = Color(0xFFF8CFC3) // Soft error background
    val OnErrorContainer = Color(0xFF6D3F18) // Dark brown for error text

    val Outline = Color(0xFF9B8E7B) // Subtle grayish brown
    val OutlineVariant = Color(0xFFD1C0A1) // Softer beige
    val Scrim = Color(0x80000000) // Semi-transparent black
}

// Colores para el modo oscuro
object DarkColorTokens {
    val Primary = Color(0xFFF5D3A4) // Light warm brown
    val OnPrimary = Color(0xFF5F3B22) // Darker brown for contrast
    val PrimaryContainer = Color(0xFF7A5535) // Rich brown
    val OnPrimaryContainer = Color(0xFFEDD2B0) // Light beige
    val InversePrimary = Color(0xFF8C5E3C) // Warm brown for inverse primary

    val Secondary = Color(0xFFB1C4A0) // Lighter olive green
    val OnSecondary = Color(0xFF3D4C37) // Darker green
    val SecondaryContainer = Color(0xFF5A6A4D) // Softer green
    val OnSecondaryContainer = Color(0xFFB1C4A0) // Lighter green

    val Tertiary = Color(0xFF4B5C3A) // Muted green
    val OnTertiary = Color(0xFFCDD7A2) // Light beige for contrast
    val TertiaryContainer = Color(0xFF2A3C27) // Darker green container
    val OnTertiaryContainer = Color(0xFFD9D9D9) // Light gray text on tertiary container

    val Background = Color(0xFF2B2B2B) // Dark gray
    val OnBackground = Color(0xFFD5D5D5) // Light gray
    val Surface = Color(0xFF2B2B2B) // Dark gray for surfaces
    val OnSurface = Color(0xFFD5D5D5) // Light gray for surface text
    val SurfaceVariant = Color(0xFF3E3D2D) // Dark olive brown
    val OnSurfaceVariant = Color(0xFFD1C0A1) // Soft beige
    val InverseSurface = Color(0xFF4A4A4A) // Dark gray
    val InverseOnSurface = Color(0xFFF5F1E1) // Light beige


    val SurfaceBright = Color(0xFF7A5535) // Rich brown for bright surfaces
    val SurfaceContainer = Color(0xFF3E3D2D) // Dark olive brown for containers
    val SurfaceContainerHigh = Color(0xFF7A5535) // Rich brown for high containers
    val SurfaceContainerHighest = Color(0xFF6C4E3B) // Deeper brown for highest containers

    val Error = Color(0xFFF0A15D) // Muted burnt orange
    val OnError = Color(0xFF5A2E11) // Dark brown for error text
    val ErrorContainer = Color(0xFF9C4C1A) // Darker background for error
    val OnErrorContainer = Color(0xFFF8CFC3) // Soft error text background

    val Outline = Color(0xFF7D6A55) // Darker brown for outline
    val OutlineVariant = Color(0xFF4A3F32) // Deeper olive brown
    val Scrim = Color(0x80000000) // Semi-transparent black
}