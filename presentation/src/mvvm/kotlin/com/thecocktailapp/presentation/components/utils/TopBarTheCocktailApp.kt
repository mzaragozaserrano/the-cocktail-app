package com.thecocktailapp.presentation.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.utils.extensions.conditional
import com.mzs.core.presentation.utils.generic.emptyText

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarTheCocktailApp(loading: Boolean, tag: String, title: String, onIconClicked: () -> Unit) {
    TopAppBar(
        modifier = Modifier.testTag(tag = tag),
        colors = TopAppBarDefaults.topAppBarColors(
            actionIconContentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {
            Text(
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall,
                text = title
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .conditional(loading.not()) { clip(shape = CircleShape).clickable { onIconClicked() } }
                    .padding(all = 8.dp),
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = emptyText
            )
        },
    )
}