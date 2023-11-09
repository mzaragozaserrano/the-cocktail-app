package com.thecocktailapp.presentation.view.utils.mvi

import com.mzaragozaserrano.presentation.view.vo.MinimalButtonVO
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.presentation.common.vo.ErrorVO

sealed class CommonResult : HomeResult, KotlinResult, SplashResult, CocktailResult {
    object Idle : CommonResult()
}

sealed interface HomeResult {
    data class Init(
        val buttonCompose: MinimalButtonVO,
        val buttonKotlin: MinimalButtonVO,
    ) : HomeResult

    sealed class Task : HomeResult {
        data class Success (val task: HomeTask): Task()
    }

}

sealed class HomeTask  {
    object NavigateToComposeModule : HomeTask()
    object NavigateToKotlinModule : HomeTask()
}

sealed interface KotlinResult {
    object Init : KotlinResult
    sealed class Task : KotlinResult {
        data class Success(val task: KotlinTask) : Task()
    }
}

sealed class KotlinTask  {
    object NavigateToCocktailFragment: KotlinTask()
    object NavigateToSplashFragment: KotlinTask()
}

sealed interface SplashResult {
    data class Init(val drink: DrinkBO?) : SplashResult
    sealed class Task : SplashResult {
        object Loading : Task()
        data class Error(val error: ErrorVO) : Task()
        data class Success(val task: SplashTask) : Task()
    }
}

sealed class SplashTask  {
    object NavigateToDrinkDetail: SplashTask()
    object NavigateToCocktailFragment: SplashTask()
    data class RandomCocktailGotten(val drink: DrinkBO): SplashTask()
}

sealed interface CocktailResult {
    object Init : CocktailResult
    sealed class Task : CocktailResult {
        object Loading : Task()
        data class Error(val error: ErrorVO) : Task()
    }
}