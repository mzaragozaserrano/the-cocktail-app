package com.thecocktailapp.com.thecocktailapp.core.presentation.compose.utils.mvi

import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.presentation.vo.ErrorVO

sealed class CommonResult : KotlinResult, SplashResult, HomeResult,
    DetailDrinkResult {
    object Idle : CommonResult()
}

sealed class HomeTask {
    object NavigateToComposeModule : HomeTask()
    object NavigateToKotlinModule : HomeTask()
}

sealed interface KotlinResult {
    object Init : KotlinResult
    sealed class Task : KotlinResult {
        data class Success(val task: KotlinTask) : Task()
    }
}

sealed class KotlinTask {
    object NavigateToHomeFragment : KotlinTask()
    object NavigateToSplashFragment : KotlinTask()
}

sealed interface SplashResult {
    data class Init(val drink: DrinkBO?) : SplashResult
    sealed class Task : SplashResult {
        object Loading : Task()
        data class Error(val error: ErrorVO) : Task()
        data class Success(val task: SplashTask) : Task()
    }
}

sealed class SplashTask {
    data class NavigateToDrinkDetail(val id: Int) : SplashTask()
    object NavigateToHomeFragment : SplashTask()
    data class DrinkGotten(val drink: DrinkBO) : SplashTask()
}

sealed interface HomeResult {
    object Init : HomeResult
    sealed class Task : HomeResult {
        object Loading : Task()
        data class Error(val error: ErrorVO) : Task()
    }
}

sealed interface DetailDrinkResult {
    data class Init(val drink: DrinkBO?) : DetailDrinkResult
    sealed class Task : DetailDrinkResult {
        object Loading : Task()
        data class Error(val error: ErrorVO) : Task()
        data class Success(val task: DetailDrinkTask) : Task()
    }
}

sealed class DetailDrinkTask {
    data class DrinkGotten(val drink: DrinkBO) : DetailDrinkTask()
}