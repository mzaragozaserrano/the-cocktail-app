package com.thecocktailapp.domain.usecases

import com.mzaragozaserrano.domain.usecases.SyncUseCaseNoParams
import com.thecocktailapp.domain.repositories.KotlinRepository
import javax.inject.Inject

typealias ShowRandomDrink = SyncUseCaseNoParams<@JvmSuppressWildcards Boolean>

class ShowRandomDrinkImpl @Inject constructor(
    private val kotlinRepository: KotlinRepository,
) : ShowRandomDrink() {
    override fun invoke(): Boolean = kotlinRepository.showRandomCocktail()
}