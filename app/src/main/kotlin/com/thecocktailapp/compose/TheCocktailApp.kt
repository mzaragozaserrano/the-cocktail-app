package com.thecocktailapp.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mzaragozaserrano.presentation.utils.navigation.Navigation
import com.thecocktailapp.compose.ui.theme.TheCocktailAppTheme

@Composable
fun TheCocktailApp() {

    val navController = rememberNavController()

    TheCocktailScreen {
        Navigation(modifier = Modifier.fillMaxSize(), navController = navController)
    }

}

@Composable
fun TheCocktailScreen(content: @Composable () -> Unit) {
    TheCocktailAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TheCocktailAppPrev() {
    TheCocktailApp()
}
