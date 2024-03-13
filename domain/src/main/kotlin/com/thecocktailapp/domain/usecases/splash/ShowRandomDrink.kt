package com.thecocktailapp.domain.usecases.splash

import com.thecocktailapp.core.domain.usecases.SyncUseCaseNoParams
import com.thecocktailapp.domain.repositories.services.CocktailRepository
import javax.inject.Inject

typealias ShowRandomDrink = SyncUseCaseNoParams<@JvmSuppressWildcards Boolean>

class ShowRandomDrinkImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
) : ShowRandomDrink() {

    override fun invoke(): Boolean = cocktailRepository.showRandomCocktail()

}