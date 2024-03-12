package com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.extensions

import com.thecocktailapp.presentation.R

fun String.getGreetingText(): Int = when (this.split(" ")[1].split(":")[0].toInt()) {
    in 6..11 -> R.string.menu_greeting_morning
    in 12..18 -> R.string.menu_greeting_afternoon
    else -> R.string.menu_greeting_night
}