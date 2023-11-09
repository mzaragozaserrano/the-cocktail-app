package com.thecocktailapp.data.datasources


interface PreferencesDataSource {
    fun getFirstAccessDate(): String?
    fun saveFirstAccessDate(accessDate: String)
}