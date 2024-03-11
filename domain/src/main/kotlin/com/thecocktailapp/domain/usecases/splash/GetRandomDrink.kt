package com.thecocktailapp.domain.usecases.splash

import com.mzs.core.domain.repositories.NetworkRepository
import com.mzs.core.domain.usecases.FlowUseCaseNoParams
import com.mzs.core.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetRandomDrink = FlowUseCaseNoParams<@JvmSuppressWildcards Result<CocktailBO>, ErrorBO>

class GetRandomDrinkUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetRandomDrink(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {

    override suspend fun run(): Flow<Result<CocktailBO>> = cocktailRepository.getRandomDrink()

}