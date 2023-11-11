package com.thecocktailapp.usecases

import com.mzaragozaserrano.domain.repositories.NetworkRepository
import com.mzaragozaserrano.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import com.thecocktailapp.domain.usecases.GetRandomDrink
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeGetRandomDrinkUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetRandomDrink(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {
    override suspend fun run(): Flow<Result<CocktailBO>> = cocktailRepository.getRandomDrink()
}