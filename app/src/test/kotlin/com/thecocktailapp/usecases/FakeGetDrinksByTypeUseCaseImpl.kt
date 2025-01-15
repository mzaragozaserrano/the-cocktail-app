package com.thecocktailapp.usecases

/*import com.mzs.core.domain.bo.Result
import com.mzs.core.domain.repositories.NetworkRepository
import com.thecocktailapp.domain.bo.CocktailBO
import com.thecocktailapp.domain.bo.ErrorBO
import com.thecocktailapp.domain.repositories.remote.CocktailRepository
import com.thecocktailapp.domain.usecases.home.GetDrinksByType
import com.thecocktailapp.domain.usecases.home.GetDrinksByTypeUseCaseImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject*/

/*
class FakeGetDrinksByTypeUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    networkRepository: NetworkRepository,
) : GetDrinksByType(networkRepository = networkRepository, networkError = ErrorBO.Connectivity) {

    private var dbId: Int = 0

    fun setAlcoholicType(dbId: Int) {
        this.dbId = dbId
    }

    override suspend fun run(params: GetDrinksByTypeUseCaseImpl.Params): Flow<Result<CocktailBO>> =
        cocktailRepository.getDrinksByType(dbId = dbId)

}*/
