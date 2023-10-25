package com.thecocktailapp.presentation.view.utils.mvi

import com.mzaragozaserrano.presentation.view.vo.MinimalButtonVO

sealed class CommonResult : HomeResult, KotlinResult {
    object Idle : CommonResult()
}

sealed interface HomeResult {
    data class Init(
        val buttonCompose: MinimalButtonVO,
        val buttonKotlin: MinimalButtonVO,
    ) : HomeResult

    sealed class Task: HomeResult {
        sealed class Success: Task() {
            object GoToKotlinModule : Success()
        }
    }

}

sealed interface KotlinResult {
    object Init: KotlinResult
}