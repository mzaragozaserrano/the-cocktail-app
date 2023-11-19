package com.thecocktailapp.repositories

import com.mzaragozaserrano.domain.repositories.NetworkRepository
import javax.inject.Inject

class FakeNetworkRepositoryImpl @Inject constructor() : NetworkRepository {
    private var isConnected: Boolean = true

    override fun isConnected(): Boolean = isConnected

    fun setConnectionStatus(isConnected: Boolean) {
        this.isConnected = isConnected
    }

}