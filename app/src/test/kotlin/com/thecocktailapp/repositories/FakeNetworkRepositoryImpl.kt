package com.thecocktailapp.repositories

import com.thecocktailapp.core.domain.repositories.NetworkRepository
import javax.inject.Inject

class FakeNetworkRepositoryImpl @Inject constructor() : NetworkRepository {
    private var isConnected: Boolean = true

    override fun isConnected(): Boolean = isConnected

    fun setConnectionStatus(isConnected: Boolean) {
        this.isConnected = isConnected
    }

}