package com.thecocktailapp.presentation.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.thecocktailapp.presentation.compose.screens.ComposeScreen
import com.thecocktailapp.presentation.compose.theme.TheCocktailAppTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheCocktailAppTheme {
                ComposeScreen()
            }
        }
    }

}