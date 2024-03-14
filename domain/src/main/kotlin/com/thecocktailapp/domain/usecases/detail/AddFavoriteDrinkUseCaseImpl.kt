package com.thecocktailapp.domain.usecases.detail

import com.thecocktailapp.core.domain.usecases.SyncUseCase
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import javax.inject.Inject

typealias AddFavoriteDrink = SyncUseCase<AddFavoriteDrinkUseCaseImpl.Params, @JvmSuppressWildcards Boolean>

class AddFavoriteDrinkUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : AddFavoriteDrink() {

    data class Params(val drink: DrinkBO)

    override fun invoke(params: Params): Boolean =
        favoritesRepository.addDrink(drink = params.drink)

}