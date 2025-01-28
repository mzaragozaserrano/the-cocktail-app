package com.thecocktailapp.presentation.screens.favorites

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mzs.core.presentation.components.compose.utils.Adapter
import com.mzs.core.presentation.utils.functions.SingleEventEffect
import com.mzs.core.presentation.utils.generic.ItemOrientation
import com.mzs.core.presentation.utils.generic.nullInt
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.items.DrinkItem
import com.thecocktailapp.presentation.components.utils.ErrorDialog
import com.thecocktailapp.presentation.components.utils.TopBarTheCocktailApp
import com.thecocktailapp.presentation.utils.FAVORITE_TOOLBAR
import com.thecocktailapp.presentation.utils.HOME_RECYCLER_VIEW
import com.thecocktailapp.presentation.viewmodels.FavoritesViewModel
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.ErrorVO
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    drinkId: Int,
    onGoBack: (Boolean?) -> Unit,
    onGoToDetail: (DrinkVO) -> Unit,
    viewModel: FavoritesViewModel = koinViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(onBack = { viewModel.onGoBack() })

    LaunchedEffect(key1 = Unit) {
        viewModel.onExecuteGetFavorites()
    }

    LaunchedEffect(key1 = drinkId) {
        if (drinkId != nullInt) {
            viewModel.onRefreshList(drinkId = drinkId)
        }
    }

    SingleEventEffect(sideEffectFlow = viewModel.navigationCompose) { element ->
        onGoBack(element as Boolean)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarTheCocktailApp(
                loading = uiState.loading,
                tag = FAVORITE_TOOLBAR,
                title = stringResource(id = R.string.toolbar_title_favorites),
                onIconClicked = { viewModel.onGoBack() }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(paddingValues = paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                content = {
                    uiState.success?.let { success ->
                        Adapter(
                            modifier = Modifier
                                .padding(end = 8.dp, start = 8.dp, top = 16.dp)
                                .testTag(tag = HOME_RECYCLER_VIEW),
                            contentPadding = 16.dp,
                            gridCells = GridCells.Fixed(count = 2),
                            itemOrientation = ItemOrientation.Vertical,
                            item = { index, item ->
                                DrinkItem(
                                    modifier = Modifier.padding(all = 8.dp),
                                    isFirstItem = index == 0,
                                    drink = item,
                                    onDrinkClicked = { onGoToDetail(item) }
                                )
                            },
                            items = success.drinks,
                            key = { _, drink -> drink.id }
                        )
                    }
                    uiState.error?.let { error ->
                        ErrorDialog(
                            buttonText = if (error is ErrorVO.FavoritesNotFound) {
                                stringResource(id = R.string.back_button)
                            } else {
                                stringResource(id = R.string.retry_button)
                            },
                            durationMillisBlockingButton = if (error is ErrorVO.FavoritesNotFound) {
                                null
                            } else {
                                3000
                            },
                            messageText = stringResource(id = error.messageId),
                            onButtonClicked = {
                                if (error is ErrorVO.FavoritesNotFound) {
                                    viewModel.onGoBack()
                                } else {
                                    viewModel.onExecuteGetFavorites()
                                }
                            }
                        )
                    }
                }
            )
        }
    )
}