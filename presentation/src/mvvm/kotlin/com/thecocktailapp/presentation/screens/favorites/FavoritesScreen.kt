package com.thecocktailapp.presentation.screens.favorites

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.utils.WarningDialog
import com.thecocktailapp.presentation.utils.FAVORITE_TOOLBAR
import com.thecocktailapp.presentation.viewmodels.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()

    BackHandler(
        enabled = true,
        onBack = { navController.popBackStack() }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.onExecuteGetFavorites()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag(tag = FAVORITE_TOOLBAR),
                title = {
                    Text(
                        color = MaterialTheme.colorScheme.onSurface,
                        text = stringResource(id = R.string.toolbar_title_favorites)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
        when (state) {
            is FavoritesViewModel.FavoritesUiState.Error -> {
                val error =
                    (state as FavoritesViewModel.FavoritesUiState.Error).error
                WarningDialog(buttonTextId = R.string.ok_button, messageTextId = error.messageId) {

                }
            }

            is FavoritesViewModel.FavoritesUiState.Idle -> {}

            is FavoritesViewModel.FavoritesUiState.Success -> {
                val list = (state as FavoritesViewModel.FavoritesUiState.Success).list
                Box(
                    modifier = Modifier.padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    )
                ) {
                    /*Recycler(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
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
}