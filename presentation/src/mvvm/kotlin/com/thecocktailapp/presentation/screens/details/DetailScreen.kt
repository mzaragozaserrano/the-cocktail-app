package com.thecocktailapp.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mzs.core.presentation.components.compose.backgrounds.RoundedBackground
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.utils.ErrorDialog
import com.thecocktailapp.presentation.components.utils.ProgressDialog
import com.thecocktailapp.presentation.utils.ChangesPreviewScreen
import com.thecocktailapp.presentation.utils.DETAIL_TOOLBAR
import com.thecocktailapp.presentation.utils.navigation.Feature
import com.thecocktailapp.presentation.utils.navigation.NavCommand
import com.thecocktailapp.presentation.viewmodels.DetailDrinkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailDrinkViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()

//    var ingredients by remember { mutableStateOf(value = listOf<String>()) }
//    val isFavorite by viewModel.isFavorite.collectAsState()
//    val initialFavoriteValue by viewModel.initialFavoriteValue.collectAsState()
//    var showInstructions by remember { mutableStateOf(value = false) }

    /*   BackHandler(
           onBack = {
               onCheckBackHandler(
                   hasChanged = initialFavoriteValue != isFavorite,
                   navController = navController
               )
           }
       )*/

    /*    LaunchedEffect(key1 = ingredients) {
            if (ingredients.isNotEmpty()) {
                showInstructions = true
            }
        }*/

    Scaffold(
        modifier = modifier,
        topBar = { TheCocktailAppTopBar(navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(paddingValues = paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = {
                    uiState.success?.let { success ->
                        RoundedBackground(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .weight(weight = 1f, fill = false),
                            backgroundColor = MaterialTheme.colorScheme.background,
                            cornerRadius = 16.dp
                        ) {
                            DetailHeaderContent(
                                drinkType = success.drink.drinkType,
                                glass = success.drink.glass,
                                ingredients = success.drink.ingredients,
                                name = success.drink.name.uppercase(),
                                url = success.drink.urlImage
                            )
                            DetailReceiptContent(
                                modifier = Modifier.fillMaxWidth(),
                                instructions = success.drink.instructions
                            )
                        }
                        SmallFloatingActionButton(
                            modifier = Modifier.padding(vertical = 16.dp),
                            onClick = {
                                if (success.isFavorite) {
                                    viewModel.removeFavoriteDrink(drink = success.drink)
                                } else {
                                    viewModel.addFavoriteDrink(drink = success.drink)
                                }
                            },
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Icon(
                                imageVector = if (success.isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "FavoriteButton"
                            )
                        }
                    }
                    if (uiState.loading) {
                        ProgressDialog()
                    }
                    uiState.error?.let { error ->
                        ErrorDialog(
                            buttonText = stringResource(id = R.string.retry_button),
                            messageText = stringResource(id = error.messageId),
                            onButtonClicked = { viewModel.onExecuteGetDrinkById(id = "") }
                        )
                    }
                }
            )
        }
    )

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TheCocktailAppTopBar(navController: NavController) {
    TopAppBar(
        modifier = Modifier.testTag(tag = DETAIL_TOOLBAR),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        title = {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(id = R.string.toolbar_title_details)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onCheckBackHandler(
                        hasChanged = false,
//                            hasChanged = initialFavoriteValue != isFavorite,
                        navController = navController
                    )
                },
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = "ArrowBackButton"
                    )
                }
            )
        },
    )
}

private fun onCheckBackHandler(hasChanged: Boolean, navController: NavController) {
    val backStackEntry = navController.previousBackStackEntry
    val previousDestination = backStackEntry?.destination?.route
    if (previousDestination == NavCommand.App(Feature.Splash).route) {
        navController.navigate(route = NavCommand.App(feature = Feature.Home).route)
    } else {
        ChangesPreviewScreen.notifyChange(hasChanged = hasChanged)
        navController.popBackStack()
    }
}