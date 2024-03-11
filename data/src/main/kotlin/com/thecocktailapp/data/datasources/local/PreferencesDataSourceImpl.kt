package com.thecocktailapp.data.datasources.local

import com.thecocktailapp.core.data.utils.encryption.EncryptedPreferences
import com.thecocktailapp.domain.utils.PreferencesKey
import javax.inject.Inject

class PreferencesDataSourceImpl @Inject constructor(private val preferences: EncryptedPreferences) :
    PreferencesDataSource {

    override fun getFirstAccessDate(): String? = preferences.getForm(PreferencesKey.AccessDate.key)

    override fun saveFirstAccessDate(accessDate: String) {
        preferences.saveForm(PreferencesKey.AccessDate.key, accessDate)
    }

}