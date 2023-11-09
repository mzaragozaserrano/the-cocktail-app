package com.thecocktailapp.data.repositories

import com.mzaragozaserrano.domain.utils.getCurrentDate
import com.mzaragozaserrano.domain.utils.sdf
import com.thecocktailapp.data.datasources.PreferencesDataSource
import com.thecocktailapp.domain.repositories.KotlinRepository
import javax.inject.Inject

class KotlinRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) : KotlinRepository {

    override fun showRandomCocktail(): Boolean {
        val currentDate = getCurrentDate(sdf)
        val accessDate = preferencesDataSource.getFirstAccessDate()
        return accessDate == null || accessDate != currentDate
    }

}