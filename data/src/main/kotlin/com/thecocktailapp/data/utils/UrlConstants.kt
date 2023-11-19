package com.thecocktailapp.data.utils

sealed class UrlConstants(val url: String) {

    object GetRandomDrink :
        UrlConstants(url = "https://www.thecocktaildb.com/api/json/v1/1/random.php")

    data class GetDrinkById(val id: Int) :
        UrlConstants(url = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=${id}")

}