package com.thecocktailapp.presentation.components.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.mzs.core.presentation.utils.generic.emptyText
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.utils.DETAIL_TOOLBAR

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TheCocktailAppTopBar(onIconClicked: () -> Unit) {
    TopAppBar(
        modifier = Modifier.testTag(tag = DETAIL_TOOLBAR),
        colors = TopAppBarDefaults.topAppBarColors(
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary,
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        title = {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(id = R.string.toolbar_title_details)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onIconClicked,
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = emptyText
                    )
                }
            )
        },
    )
}