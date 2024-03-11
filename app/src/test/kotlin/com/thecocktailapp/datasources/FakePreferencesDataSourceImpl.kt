package com.thecocktailapp.datasources

import com.thecocktailapp.data.datasources.local.PreferencesDataSource
import javax.inject.Inject

class FakePreferencesDataSourceImpl @Inject constructor() : PreferencesDataSource {

    private var isFirstAccess: Boolean = false

    fun setIsFirstsAccess(isFirstAccess: Boolean) {
        this.isFirstAccess = isFirstAccess
    }

    override fun getFirstAccessDate(): String? = if (isFirstAccess) {
        null
    } else {
        "11/03/2024"
    }

    override fun saveFirstAccessDate(accessDate: String) {

    }

}