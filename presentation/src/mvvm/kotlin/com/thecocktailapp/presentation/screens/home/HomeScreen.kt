package com.thecocktailapp.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thecocktailapp.presentation.components.ErrorDialog
import com.thecocktailapp.presentation.components.ProgressDialog
import com.thecocktailapp.presentation.viewmodels.home.HomeViewModel
import com.thecocktailapp.ui.R
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
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Rounded.Menu,
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
                    is HomeViewModel.HomeUiState.Error -> {
                        val error = (state as HomeViewModel.HomeUiState.Error).error
                        ErrorDialog(
                            buttonTextId = R.string.retry_button,
                            messageTextId = error.messageId
                        ) {
                            viewModel.onExecuteGetDrinksByType()
                        }
                    }

                    is HomeViewModel.HomeUiState.Idle -> {}
                    is HomeViewModel.HomeUiState.Loading -> {
                        ProgressDialog()
                    }

                    is HomeViewModel.HomeUiState.Success -> {
                        Text(text = (state as HomeViewModel.HomeUiState.Success).list.first().name)
                    }
                }
            }
        }
    }
}