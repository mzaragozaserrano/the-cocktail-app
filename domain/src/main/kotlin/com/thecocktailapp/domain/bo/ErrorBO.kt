package com.thecocktailapp.domain.bo

sealed class ErrorBO {
    object Connectivity: ErrorBO()
    object DataNotFound : ErrorBO()
    object DeserializingJSON : ErrorBO()
    data class Generic(val id: Int) : ErrorBO()
    object LoadingData : ErrorBO()
    object LoadingURL : ErrorBO()
}