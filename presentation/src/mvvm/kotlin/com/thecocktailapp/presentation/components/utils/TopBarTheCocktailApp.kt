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
import com.mzs.core.presentation.utils.generic.emptyText

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarTheCocktailApp(tag: String, title: String, onIconClicked: () -> Unit) {
    TopAppBar(
        modifier = Modifier.testTag(tag = tag),
        colors = TopAppBarDefaults.topAppBarColors(
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary,
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        title = {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = title
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