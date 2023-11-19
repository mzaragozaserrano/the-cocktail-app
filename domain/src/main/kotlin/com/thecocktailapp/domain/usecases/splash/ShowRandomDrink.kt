package com.thecocktailapp.domain.usecases.splash

import com.mzaragozaserrano.domain.usecases.SyncUseCaseNoParams
import com.thecocktailapp.domain.repositories.CocktailRepository
import javax.inject.Inject

typealias ShowRandomDrink = SyncUseCaseNoParams<@JvmSuppressWildcards Boolean>

class ShowRandomDrinkImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
) : ShowRandomDrink() {

    override fun invoke(): Boolean = cocktailRepository.showRandomCocktail()

}