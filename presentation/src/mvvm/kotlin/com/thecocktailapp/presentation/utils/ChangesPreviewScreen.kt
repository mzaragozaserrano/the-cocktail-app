package com.thecocktailapp.presentation.utils

import kotlinx.coroutines.flow.MutableStateFlow

object ChangesPreviewScreen {
    val hasChanged = MutableStateFlow(value = false)
    fun notifyChange(hasChanged: Boolean) {
        this.hasChanged.value = hasChanged
    }
}