package com.thecocktailapp.presentation.screens.home

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.utils.ErrorDialog
import com.thecocktailapp.presentation.components.utils.MenuNavigation
import com.thecocktailapp.presentation.components.utils.ProgressDialog
import com.thecocktailapp.presentation.utils.ChangesPreviewScreen
import com.thecocktailapp.presentation.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        ChangesPreviewScreen.hasChanged.collect { hasChanged ->
            if (hasChanged) {
                viewModel.onExecuteGetFavorites()
            }
        }
    }

    MenuNavigation(
        modifier = modifier,
        drawerState = drawerState,
        navController = navController,
        onHomeRefreshed = { viewModel.onExecuteGetDrinksByType() }
    ) {
        HeaderFilterType { drinkType ->
            viewModel.onTypeClicked(drinkType)
        }
        when (state) {
            is HomeViewModel.HomeUiState.Error -> {
                val error = (state as HomeViewModel.HomeUiState.Error).error
                ErrorDialog(
                    buttonText = stringResource(id = R.string.retry_button),
                    messageText = stringResource(id = error.messageId)
                ) {
                    viewModel.onExecuteGetDrinksByType()
                }
            }

            is HomeViewModel.HomeUiState.Idle -> {}

            is HomeViewModel.HomeUiState.Loading -> {
                ProgressDialog()
            }

            is HomeViewModel.HomeUiState.Success -> {
                val list = (state as HomeViewModel.HomeUiState.Success).list
                /*Adapter(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .testTag(tag = HOME_RECYCLER_VIEW),
                    list = list,
                    numberCells = 2
                ) { item ->
                    DrinkItem(
                        modifier = Modifier.padding(all = 8.dp),
                        isFirstItem = list.indexOf(item) == 0,
                        item = item
                    ) {
                        navController.navigate(
                            route = NavCommand.Content(feature = Feature.Detail)
                                .createRoute(drinkId = item.id)
                        )
                    }
                }*/
            }
        }
    }
}