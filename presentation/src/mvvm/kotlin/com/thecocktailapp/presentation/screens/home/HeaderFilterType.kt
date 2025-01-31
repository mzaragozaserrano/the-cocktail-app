package com.thecocktailapp.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mzs.core.presentation.utils.generic.emptyText
import com.thecocktailapp.presentation.vo.DrinkType
import com.thecocktailapp.presentation.vo.getDrinkTypeList

@Composable
fun HeaderFilterType(
    modifier: Modifier = Modifier,
    loading: Boolean,
    value: Int,
    onTypeClicked: (DrinkType) -> Unit,
) {

    val list = getDrinkTypeList()
    var selectedIndex by remember { mutableIntStateOf(value = value) }

    SingleChoiceSegmentedButtonRow(
        modifier = modifier,
        content = {
            list.forEachIndexed { index, drinkType ->
                SegmentedButton(
                    colors = SegmentedButtonDefaults.colors(
                        activeBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        activeContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        activeContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledActiveBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledActiveContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledActiveContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledInactiveBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledInactiveContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledInactiveContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        inactiveBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                        inactiveContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        inactiveContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    enabled = loading.not(),
                    icon = {
                        if (selectedIndex == index) {
                            Icon(
                                modifier = Modifier.padding(start = 16.dp),
                                imageVector = Icons.Rounded.Check,
                                contentDescription = emptyText
                            )
                        }
                    },
                    label = {
                        Text(
                            modifier = Modifier.padding(end = 4.dp, start = 16.dp),
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelLarge,
                            text = stringResource(id = drinkType.nameId)
                        )
                    },
                    selected = index == selectedIndex,
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = list.size),
                    onClick = {
                        if (selectedIndex != index) {
                            onTypeClicked(drinkType)
                            selectedIndex = index
                        }
                    }
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun HeaderFilterTypePrev() {
    HeaderFilterType(
        loading = false,
        value = 0,
        onTypeClicked = { /*Here will go the action when clicking one option */ }
    )
}