package com.thecocktailapp.usecases

import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import com.thecocktailapp.domain.usecases.common.GetFavoriteDrinks
import javax.inject.Inject

class FakeGetFavoriteDrinksUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : GetFavoriteDrinks() {
    override fun invoke(): List<DrinkBO> = favoritesRepository.getAllFavorites()
}