package com.thecocktailapp.tests

import android.content.Context
import android.net.ConnectivityManager
import com.thecocktailapp.repositories.FakeNetworkRepositoryImpl
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NetworkRepositoryImplTest {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var connectivityManager: ConnectivityManager

    @InjectMocks
    private lateinit var networkRepository: FakeNetworkRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `check connectivity returns true`() {
        `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(
            connectivityManager
        )

        val isConnected = networkRepository.isConnected()
        assertTrue(isConnected)

    }

}