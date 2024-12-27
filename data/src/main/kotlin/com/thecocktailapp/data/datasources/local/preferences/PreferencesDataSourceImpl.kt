package com.thecocktailapp.data.datasources.local.preferences

import com.mzs.core.data.utils.encryption.EncryptedPreferences
import com.thecocktailapp.domain.utils.PreferencesKey
import javax.inject.Inject

class PreferencesDataSourceImpl @Inject constructor(private val preferences: EncryptedPreferences) :
    PreferencesDataSource {

    override fun getFirstAccessDate(): String? =
        preferences.getForm(tag = PreferencesKey.AccessDate.key)

    override fun saveFirstAccessDate(accessDate: String) {
        preferences.saveForm(tag = PreferencesKey.AccessDate.key, stringValue = accessDate)
    }

}