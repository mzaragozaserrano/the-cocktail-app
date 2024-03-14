package com.thecocktailapp.presentation.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thecocktailapp.core.presentation.compose.components.texts.NormalMediumText
import com.thecocktailapp.core.presentation.compose.components.utils.Recycler
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.items.DrinkItem
import com.thecocktailapp.presentation.components.utils.WarningDialog
import com.thecocktailapp.presentation.utils.navigation.Feature
import com.thecocktailapp.presentation.utils.navigation.NavCommand
import com.thecocktailapp.presentation.viewmodels.favorites.FavoritesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onExecuteGetFavorites()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            val coroutineScope = rememberCoroutineScope()
            TopAppBar(
                title = {
                    NormalMediumText(
                        color = MaterialTheme.colorScheme.onSurface,
                        textId = R.string.toolbar_title_favorites
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "MenuButton"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Surface {
            Column(modifier = Modifier.padding(paddingValues)) {
                when (state) {
                    is FavoritesViewModel.FavoritesUiState.Error -> {
                        val error =
                            (state as FavoritesViewModel.FavoritesUiState.Error).error
                        WarningDialog(
                            buttonTextId = R.string.ok_button,
                            messageTextId = error.messageId
                        ) {

                        }
                    }

                    is FavoritesViewModel.FavoritesUiState.Idle -> {}

                    is FavoritesViewModel.FavoritesUiState.Success -> {
                        Recycler(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 8.dp),
                            list = (state as FavoritesViewModel.FavoritesUiState.Success).list,
                            numberCells = 2
                        ) { item ->
                            DrinkItem(
                                modifier = Modifier.padding(all = 8.dp),
                                item = item
                            ) {
                                navController.navigate(
                                    route = NavCommand.Content(feature = Feature.Detail)
                                        .createRoute(drinkId = item.id)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}