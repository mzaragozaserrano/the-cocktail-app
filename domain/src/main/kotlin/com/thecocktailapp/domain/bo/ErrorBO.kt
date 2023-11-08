package com.thecocktailapp.domain.bo

sealed class ErrorBO {
    data class Basic(val id: Int) : ErrorBO()
    object Connectivity: ErrorBO()
    object DataNotFound : ErrorBO()
    object DeserializingJSON : ErrorBO()
    object LoadingData : ErrorBO()
    object LoadingURL : ErrorBO()
}