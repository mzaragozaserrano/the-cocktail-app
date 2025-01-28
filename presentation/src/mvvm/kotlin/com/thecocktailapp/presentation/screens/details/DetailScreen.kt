package com.thecocktailapp.presentation.screens.details

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mzs.core.presentation.components.compose.backgrounds.RoundedBackground
import com.mzs.core.presentation.utils.functions.SingleEventEffect
import com.mzs.core.presentation.utils.generic.emptyText
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.utils.ErrorDialog
import com.thecocktailapp.presentation.components.utils.ProgressDialog
import com.thecocktailapp.presentation.components.utils.TopBarTheCocktailApp
import com.thecocktailapp.presentation.utils.DETAIL_TOOLBAR
import com.thecocktailapp.presentation.viewmodels.DetailDrinkViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    drinkId: Int,
    isFavorite: Boolean,
    onGoBack: (Int?) -> Unit,
    viewModel: DetailDrinkViewModel = koinViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(onBack = { viewModel.onGoBack(drinkId = drinkId) })

    SingleEventEffect(sideEffectFlow = viewModel.navigationCompose) { element ->
        onGoBack(element as Int?)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.onExecuteGetDrinkById(idDrink = drinkId, isFavorite = isFavorite)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarTheCocktailApp(
                loading = uiState.loading,
                onIconClicked = { viewModel.onGoBack(drinkId = drinkId) },
                tag = DETAIL_TOOLBAR,
                title = stringResource(id = R.string.toolbar_title_details)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(paddingValues = paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = {
                    uiState.success?.let { success ->
                        RoundedBackground(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .weight(weight = 1f, fill = false),
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                            cornerRadius = 16.dp,
                            content = {
                                DetailHeaderContent(
                                    drinkType = success.drink.drinkType,
                                    glass = success.drink.glass,
                                    ingredients = success.drink.ingredients,
                                    name = success.drink.name.uppercase(),
                                    url = success.drink.urlImage
                                )
                                DetailReceiptContent(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp, end = 16.dp, start = 16.dp),
                                    instructions = success.drink.instructions
                                )
                            }
                        )
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
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            content = {
                                Icon(
                                    imageVector = if (success.isFavorite) {
                                        Icons.Outlined.Favorite
                                    } else {
                                        Icons.Outlined.FavoriteBorder
                                    },
                                    contentDescription = emptyText
                                )
                            }
                        )
                    }
                    if (uiState.loading) {
                        ProgressDialog()
                    }
                    uiState.error?.let { error ->
                        ErrorDialog(
                            buttonText = stringResource(id = R.string.retry_button),
                            messageText = stringResource(id = error.messageId),
                            onButtonClicked = {
                                viewModel.onExecuteGetDrinkById(
                                    idDrink = drinkId,
                                    isFavorite = isFavorite
                                )
                            }
                        )
                    }
                }
            )
        }
    )

}