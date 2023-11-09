package com.thecocktailapp.domain.utils

sealed class PreferencesKey(val key: String) {
    object AccessDate: PreferencesKey("access_date")
}
