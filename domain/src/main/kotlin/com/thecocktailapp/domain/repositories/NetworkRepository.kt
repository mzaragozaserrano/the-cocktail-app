package com.thecocktailapp.domain.repositories

interface NetworkRepository {
    fun isConnected(): Boolean
}