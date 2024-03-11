package com.thecocktailapp.presentation.utils.extensions

import com.thecocktailapp.ui.R

fun String.getGreetingText(): Int = when (this.split(" ")[1].split(":")[0].toInt()) {
    in 6..11 -> R.string.menu_greeting_morning
    in 12..18 -> R.string.menu_greeting_afternoon
    else -> R.string.menu_greeting_night
}