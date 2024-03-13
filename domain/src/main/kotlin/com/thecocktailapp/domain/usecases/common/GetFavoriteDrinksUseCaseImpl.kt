package com.thecocktailapp.domain.usecases.common

import com.thecocktailapp.core.domain.repositories.NetworkRepository
import com.thecocktailapp.core.domain.usecases.FlowUseCaseNoParams
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.local.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFavoriteDrinks = FlowUseCaseNoParams<@JvmSuppressWildcards Result<List<Int>>, ErrorBO>

class GetFavoriteDrinksUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    networkRepository: NetworkRepository,
) : GetFavoriteDrinks(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {

    override suspend fun run(): Flow<Result<List<Int>>> = favoritesRepository.getAllFavorites()

}