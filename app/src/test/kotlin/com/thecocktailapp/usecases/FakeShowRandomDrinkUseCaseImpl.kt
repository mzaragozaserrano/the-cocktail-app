package com.thecocktailapp.usecases

import com.thecocktailapp.domain.repositories.CocktailRepository
import com.thecocktailapp.domain.usecases.splash.ShowRandomDrink
import javax.inject.Inject

class FakeShowRandomDrinkUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
) : ShowRandomDrink() {

    override fun invoke(): Boolean = cocktailRepository.showRandomCocktail()

}