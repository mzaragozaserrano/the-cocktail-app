package com.thecocktailapp.domain.usecases.splash

import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.repositories.NetworkRepository
import com.mzs.core.domain.usecases.FlowUseCaseNoParams
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.remote.CocktailRepository
import kotlinx.coroutines.flow.Flow

class GetRandomDrinkUseCaseImpl(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : FlowUseCaseNoParams<Result<CocktailBO>, ErrorBO>(
    networkRepository = networkRepository,
    networkError = ErrorBO.Connectivity
) {

    override suspend fun run(): Flow<Result<CocktailBO>> = cocktailRepository.getRandomDrink()

}