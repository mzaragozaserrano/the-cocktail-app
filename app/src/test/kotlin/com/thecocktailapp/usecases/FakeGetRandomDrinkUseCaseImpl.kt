package com.thecocktailapp.usecases

import com.thecocktailapp.core.domain.repositories.NetworkRepository
import com.thecocktailapp.core.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import com.thecocktailapp.domain.usecases.splash.GetRandomDrink
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeGetRandomDrinkUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetRandomDrink(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {

    override suspend fun run(): Flow<Result<CocktailBO>> = cocktailRepository.getRandomDrink()

}