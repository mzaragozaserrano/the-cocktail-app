package com.thecocktailapp.domain.usecases.detail

import com.mzs.core.domain.usecases.SyncUseCase
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import javax.inject.Inject

typealias RemoveFavoriteDrink = SyncUseCase<RemoveFavoriteDrinkUseCaseImpl.Params, @JvmSuppressWildcards Boolean>

class RemoveFavoriteDrinkUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : RemoveFavoriteDrink() {

    data class Params(val drink: DrinkBO)

    override fun invoke(params: Params): Boolean =
        favoritesRepository.removeDrink(drink = params.drink)

}