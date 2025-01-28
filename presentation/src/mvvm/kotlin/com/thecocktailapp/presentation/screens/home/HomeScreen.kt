package com.thecocktailapp.presentation.screens.home

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mzs.core.presentation.components.compose.utils.Adapter
import com.mzs.core.presentation.utils.generic.ItemOrientation
import com.mzs.core.presentation.utils.generic.nullInt
import com.mzs.core.presentation.vo.MenuItemVO
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.items.DrinkItem
import com.thecocktailapp.presentation.components.utils.ErrorDialog
import com.thecocktailapp.presentation.components.utils.MenuNavigation
import com.thecocktailapp.presentation.components.utils.ProgressDialog
import com.thecocktailapp.presentation.utils.HOME_RECYCLER_VIEW
import com.thecocktailapp.presentation.viewmodels.HomeViewModel
import com.thecocktailapp.presentation.vo.DrinkVO
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    drinkId: Int,
    forceRefresh: Boolean,
    viewModel: HomeViewModel = koinViewModel(),
    onGoToDetail: (DrinkVO) -> Unit,
    onMenuItemClicked: (MenuItemVO) -> Unit,
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(onBack = { (context as? ComponentActivity)?.finish() })

    LaunchedEffect(key1 = uiState.success, key2 = forceRefresh) {
        if (uiState.success == null || forceRefresh) {
            viewModel.onExecuteGetDrinksByType()
        }
    }

    LaunchedEffect(key1 = drinkId) {
        if (drinkId != nullInt) {
            viewModel.onRefreshList(drinkId = drinkId)
        }
    }

    MenuNavigation(
        modifier = modifier,
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        loading = uiState.loading,
        onMenuItemClicked = onMenuItemClicked,
        content = {
            HeaderFilterType(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                loading = uiState.loading,
                value = uiState.drinkType.id,
                onTypeClicked = { drinkType ->
                    viewModel.onTypeClicked(drinkType = drinkType)
                }
            )
            if (uiState.loading) {
                ProgressDialog()
            }
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
                    buttonText = stringResource(id = R.string.retry_button),
                    messageText = stringResource(id = error.messageId),
                    onButtonClicked = { viewModel.onRetryExecuteGetDrinksByType() }
                )
            }
        }
    )

}