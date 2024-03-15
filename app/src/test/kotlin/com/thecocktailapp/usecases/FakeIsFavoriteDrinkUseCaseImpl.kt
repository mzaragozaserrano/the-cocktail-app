package com.thecocktailapp.usecases

import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import com.thecocktailapp.domain.usecases.detail.IsFavoriteDrink
import com.thecocktailapp.domain.usecases.detail.IsFavoriteDrinkUseCaseImpl
import javax.inject.Inject

class FakeIsFavoriteDrinkUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : IsFavoriteDrink() {
    override fun invoke(params: IsFavoriteDrinkUseCaseImpl.Params): Boolean =
        favoritesRepository.isFavoriteDrink(params.drinkId)

}