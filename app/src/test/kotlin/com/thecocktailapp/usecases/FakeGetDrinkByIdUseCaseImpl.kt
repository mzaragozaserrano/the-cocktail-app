package com.thecocktailapp.usecases

import com.mzaragozaserrano.domain.repositories.NetworkRepository
import com.mzaragozaserrano.domain.utils.Result
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.CocktailRepository
import com.thecocktailapp.domain.usecases.GetDrinkById
import com.thecocktailapp.domain.usecases.GetDrinkByIdUseCaseImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeGetDrinkByIdUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetDrinkById(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {

    override suspend fun run(params: GetDrinkByIdUseCaseImpl.Params): Flow<Result<CocktailBO>> = cocktailRepository.getDrinkById(15813)

}