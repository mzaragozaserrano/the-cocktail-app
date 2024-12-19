package com.thecocktailapp.presentation.screens.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mzs.core.presentation.compose.components.cards.RoundedCard
import com.mzs.core.presentation.compose.components.texts.NormalMediumText
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.utils.ErrorDialog
import com.thecocktailapp.presentation.components.utils.ProgressDialog
import com.thecocktailapp.presentation.utils.ChangesPreviewScreen
import com.thecocktailapp.presentation.utils.DETAIL_TOOLBAR
import com.thecocktailapp.presentation.utils.navigation.Feature
import com.thecocktailapp.presentation.utils.navigation.NavCommand
import com.thecocktailapp.presentation.viewmodels.detail.DetailDrinkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailDrinkViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    var ingredients by remember { mutableStateOf(value = listOf<String>()) }
    val isFavorite by viewModel.isFavorite.collectAsState()
    val initialFavoriteValue by viewModel.initialFavoriteValue.collectAsState()
    var showInstructions by remember { mutableStateOf(value = false) }

    BackHandler(
        enabled = true,
        onBack = {
            onCheckBackHandler(
                hasChanged = initialFavoriteValue != isFavorite,
                navController = navController
            )
        }
    )

    LaunchedEffect(key1 = ingredients) {
        if (ingredients.isNotEmpty()) {
            showInstructions = true
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag(tag = DETAIL_TOOLBAR),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                title = {
                    NormalMediumText(
                        color = MaterialTheme.colorScheme.onSurface,
                        textId = R.string.toolbar_title_details
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onCheckBackHandler(
                            hasChanged = initialFavoriteValue != isFavorite,
                            navController = navController
                        )
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "ArrowBackButton"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Surface {
            LazyColumn(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    when (state) {
                        is DetailDrinkViewModel.DetailDrinkUiState.Error -> {
                            val error =
                                (state as DetailDrinkViewModel.DetailDrinkUiState.Error).error
                            ErrorDialog(
                                buttonTextId = R.string.retry_button,
                                messageTextId = error.messageId
                            ) {
                                viewModel.onExecuteGetDrinkById()
                            }
                        }

                        is DetailDrinkViewModel.DetailDrinkUiState.Idle -> {}
                        is DetailDrinkViewModel.DetailDrinkUiState.Loading -> {
                            ProgressDialog()
                        }

                        is DetailDrinkViewModel.DetailDrinkUiState.Success -> {
                            val drink =
                                (state as DetailDrinkViewModel.DetailDrinkUiState.Success).drink
                            ingredients = drink.ingredients
                            Column(
                                modifier = Modifier.padding(all = 24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(space = 12.dp)
                            ) {
                                RoundedCard(backgroundColor = MaterialTheme.colorScheme.background) {
                                    DetailHeaderContent(
                                        drinkType = drink.drinkType,
                                        glass = drink.glass,
                                        name = drink.name.uppercase(),
                                        url = drink.urlImage
                                    )
                                    DetailIngredientsContent(
                                        ingredients = ingredients,
                                        showInstructions = showInstructions
                                    )
                                    DetailInstructionsContent(
                                        modifier = Modifier.fillMaxWidth(),
                                        instructions = drink.instructions
                                    )
                                }
                                SmallFloatingActionButton(
                                    onClick = {
                                        if (isFavorite) {
                                            viewModel.removeFavoriteDrink(drink = drink)
                                        } else {
                                            viewModel.addFavoriteDrink(drink = drink)
                                        }
                                    },
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                    contentColor = MaterialTheme.colorScheme.secondary
                                ) {
                                    Icon(
                                        imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = "FavoriteButton"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

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