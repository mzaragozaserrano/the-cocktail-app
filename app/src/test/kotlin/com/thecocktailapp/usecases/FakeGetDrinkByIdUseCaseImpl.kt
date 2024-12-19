package com.thecocktailapp.usecases

import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.repositories.NetworkRepository
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.services.CocktailRepository
import com.thecocktailapp.domain.usecases.detail.GetDrinkById
import com.thecocktailapp.domain.usecases.detail.GetDrinkByIdUseCaseImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeGetDrinkByIdUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetDrinkById(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {
    override suspend fun run(params: GetDrinkByIdUseCaseImpl.Params): Flow<Result<CocktailBO>> = cocktailRepository.getDrinkById(15813)
}