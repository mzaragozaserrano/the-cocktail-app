package com.thecocktailapp.domain.usecases.splash

import com.mzs.core.domain.usecases.SyncUseCaseNoParams
import com.thecocktailapp.domain.repositories.remote.CocktailRepository

class ShowRandomDrinkUseCaseImpl(private val cocktailRepository: CocktailRepository) :
    SyncUseCaseNoParams<Boolean>() {

    override fun invoke(): Boolean = cocktailRepository.showRandomCocktail()

}