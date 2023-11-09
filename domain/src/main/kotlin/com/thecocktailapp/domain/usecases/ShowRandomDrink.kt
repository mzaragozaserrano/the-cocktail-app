package com.thecocktailapp.domain.usecases

import com.mzaragozaserrano.domain.usecases.SyncUseCaseNoParams
import com.thecocktailapp.domain.repositories.KotlinRepository
import javax.inject.Inject

typealias CheckPreferencesToShowRandomDrink = SyncUseCaseNoParams<@JvmSuppressWildcards Boolean>

class CheckPreferencesToShowRandomDrinkImpl @Inject constructor(
    private val kotlinRepository: KotlinRepository,
) : CheckPreferencesToShowRandomDrink() {
    override fun invoke(): Boolean = kotlinRepository.showRandomCocktail()

}