package com.thecocktailapp.domain.usecases.detail

import com.mzs.core.domain.usecases.SyncUseCase
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.repositories.local.FavoritesRepository

class RemoveFavoriteDrinkUseCaseImpl(private val favoritesRepository: FavoritesRepository) :
    SyncUseCase<RemoveFavoriteDrinkUseCaseImpl.Params, Boolean>() {

    data class Params(val drink: DrinkBO)

    override fun invoke(params: Params): Boolean =
        favoritesRepository.removeDrink(drink = params.drink)

}