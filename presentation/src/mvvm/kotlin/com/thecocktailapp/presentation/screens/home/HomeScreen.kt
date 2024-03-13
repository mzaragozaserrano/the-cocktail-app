package com.thecocktailapp.presentation.screens.home

import androidx.compose.foundation.layout.Column
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
import com.thecocktailapp.presentation.components.utils.ErrorDialog
import com.thecocktailapp.presentation.components.utils.ProgressDialog
import com.thecocktailapp.presentation.utils.navigation.Feature
import com.thecocktailapp.presentation.utils.navigation.NavCommand
import com.thecocktailapp.presentation.viewmodels.home.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            val coroutineScope = rememberCoroutineScope()
            TopAppBar(
                title = {
                    NormalMediumText(
                        color = MaterialTheme.colorScheme.onSurface,
                        textId = R.string.toolbar_title_home
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
                HeaderFilterType { drinkType ->
                    viewModel.onTypeClicked(drinkType)
                }
                when (state) {
                    is HomeViewModel.HomeUiState.Error -> {
                        val error = (state as HomeViewModel.HomeUiState.Error).error
                        ErrorDialog(
                            buttonTextId = R.string.retry_button,
                            messageTextId = error.messageId
                        ) {
                            viewModel.onExecuteGetDrinksByType()
                        }
                    }

                    is HomeViewModel.HomeUiState.GoToDetail -> {
                        navController.navigate(
                            route = NavCommand.Content(feature = Feature.Detail)
                                .createRoute(drinkId = (state as HomeViewModel.HomeUiState.GoToDetail).drinkId)
                        )
                        viewModel.onIdle()
                    }

                    is HomeViewModel.HomeUiState.Idle -> {}
                    is HomeViewModel.HomeUiState.Loading -> {
                        ProgressDialog()
                    }

                    is HomeViewModel.HomeUiState.Success -> {
                        Recycler(
                            modifier = Modifier.padding(all = 8.dp),
                            list = (state as HomeViewModel.HomeUiState.Success).list,
                            numberCells = 2
                        ) { item ->
                            DrinkItem(
                                modifier = Modifier.padding(all = 8.dp),
                                item = item
                            ) {
                                viewModel.onSeeClicked(item)
                            }
                        }
                    }
                }
            }
        }
    }

}