package com.thecocktailapp.usecases

import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.RemoveFavoriteDrinkUseCaseImpl
import javax.inject.Inject

class FakeRemoveFavoriteDrinkUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : RemoveFavoriteDrink() {
    override fun invoke(params: RemoveFavoriteDrinkUseCaseImpl.Params): Boolean =
        favoritesRepository.removeDrink(params.drink)
}