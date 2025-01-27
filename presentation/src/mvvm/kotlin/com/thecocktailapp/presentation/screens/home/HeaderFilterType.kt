package com.thecocktailapp.presentation.screens.home

import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.thecocktailapp.presentation.vo.DrinkType
import com.thecocktailapp.presentation.vo.getDrinkTypeList

@Composable
fun HeaderFilterType(
    modifier: Modifier = Modifier,
    value: Int,
    onTypeClicked: (DrinkType) -> Unit,
) {

    var selectedIndex by remember { mutableIntStateOf(value = value) }
    val list = getDrinkTypeList()

    SingleChoiceSegmentedButtonRow(
        modifier = modifier,
        content = {
            list.forEachIndexed { index, drinkType ->
                SegmentedButton(
                    selected = index == selectedIndex,
                    onClick = {
                        if (selectedIndex != index) {
                            onTypeClicked(drinkType)
                            selectedIndex = index
                        }
                    },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = list.size),
                    label = {
                        Text(
                            modifier = Modifier.padding(all = 4.dp),
                            text = stringResource(id = drinkType.nameId)
                        )
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
        value = 0,
        onTypeClicked = { /*Here will go the action when clicking one option */ }
    )
}