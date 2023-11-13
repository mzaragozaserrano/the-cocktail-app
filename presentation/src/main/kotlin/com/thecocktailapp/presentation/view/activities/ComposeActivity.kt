package com.thecocktailapp.presentation.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.thecocktailapp.presentation.compose.screens.HomeScreen
import com.thecocktailapp.presentation.compose.theme.TheCocktailAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheCocktailAppTheme {
                HomeScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }

}