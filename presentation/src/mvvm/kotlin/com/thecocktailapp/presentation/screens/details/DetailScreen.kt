package com.thecocktailapp.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mzs.core.presentation.components.compose.backgrounds.RoundedBackground
import com.thecocktailapp.presentation.components.utils.TheCocktailAppTopBar
import com.thecocktailapp.presentation.viewmodels.DetailDrinkViewModel
import com.thecocktailapp.presentation.vo.DrinkVO

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    drink: DrinkVO,
    onIconClicked: () -> Unit,
    viewModel: DetailDrinkViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.setUpView(drink = drink)
    }

    Scaffold(
        modifier = modifier,
        topBar = { TheCocktailAppTopBar(onIconClicked = onIconClicked) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(paddingValues = paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = {
                    RoundedBackground(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(weight = 1f, fill = false),
                        backgroundColor = MaterialTheme.colorScheme.background,
                        cornerRadius = 16.dp
                    ) {
                        DetailHeaderContent(
                            drinkType = drink.drinkType,
                            glass = drink.glass,
                            ingredients = drink.ingredients,
                            name = drink.name.uppercase(),
                            url = drink.urlImage
                        )
                        DetailReceiptContent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp, end = 16.dp, start = 16.dp),
                            instructions = drink.instructions
                        )
                    }
                    SmallFloatingActionButton(
                        modifier = Modifier.padding(vertical = 16.dp),
                        onClick = {
                            if (uiState.isFavorite) {
                                viewModel.removeFavoriteDrink(drink = drink)
                            } else {
                                viewModel.addFavoriteDrink(drink = drink)
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary
                    ) {
                        Icon(
                            imageVector = if (uiState.isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "FavoriteButton"
                        )
                    }
                }
            )
        }
    )

}