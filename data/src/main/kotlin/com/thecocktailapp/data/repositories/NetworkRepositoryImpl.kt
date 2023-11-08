package com.thecocktailapp.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import com.thecocktailapp.domain.repositories.NetworkRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    NetworkRepository {
    override fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities != null
    }
}
