package com.thecocktailapp.data.datasources.local

interface PreferencesDataSource {
    fun getFirstAccessDate(): String?
    fun saveFirstAccessDate(accessDate: String)
}