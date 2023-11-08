package com.thecocktailapp.presentation.view.utils.mvi

import com.mzaragozaserrano.presentation.view.vo.MinimalButtonVO
import com.thecocktailapp.domain.bo.DrinkBO
import com.thecocktailapp.presentation.common.vo.ErrorVO

sealed class CommonResult : HomeResult, KotlinResult, CocktailResult {
    object Idle : CommonResult()
}

sealed interface HomeResult {
    data class Init(
        val buttonCompose: MinimalButtonVO,
        val buttonKotlin: MinimalButtonVO,
    ) : HomeResult

    sealed class Task : HomeResult {
        sealed class Success : Task() {
            object GoToComposeModule : Success()
            object GoToKotlinModule : Success()
        }
    }

}

sealed interface KotlinResult {
    object Init : KotlinResult
}

sealed interface CocktailResult {
    object Init : CocktailResult
    sealed class Task : CocktailResult {
        object Loading : Task()
        data class Error(val error: ErrorVO) : Task()
        data class Success(val task: CocktailTask) : Task() {

        }
    }
}

sealed class CocktailTask {
    data class RandomCocktailGotten(val drink: DrinkBO) : CocktailTask()
}