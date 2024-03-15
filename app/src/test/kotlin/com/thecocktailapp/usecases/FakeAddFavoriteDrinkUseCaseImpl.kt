package com.thecocktailapp.usecases

import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.AddFavoriteDrinkUseCaseImpl
import javax.inject.Inject

class FakeAddFavoriteDrinkUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : AddFavoriteDrink() {
    override fun invoke(params: AddFavoriteDrinkUseCaseImpl.Params): Boolean =
        favoritesRepository.addDrink(params.drink)

}