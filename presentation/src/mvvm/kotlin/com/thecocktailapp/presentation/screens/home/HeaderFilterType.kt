package com.thecocktailapp.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.presentation.compose.components.texts.SmallMediumText
import com.thecocktailapp.presentation.vo.DrinkType
import com.thecocktailapp.presentation.vo.getDrinkTypeList

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HeaderFilterType(onTypeClicked: (DrinkType) -> Unit) {

    var selectedIndex by remember { mutableStateOf(0) }
    val list = getDrinkTypeList()

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        list.forEachIndexed { index, drinkType ->
            SegmentedButton(
                selected = index == selectedIndex,
                onClick = {
                    if (selectedIndex != index) {
                        onTypeClicked(drinkType)
                        selectedIndex = index
                    }
                },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = list.size)
            ) {
                SmallMediumText(
                    modifier = Modifier.padding(all = 4.dp),
                    textId = drinkType.nameId
                )
            }
        }
    }

}

@Preview
@Composable
private fun HeaderFilterTypePrev() {
    HeaderFilterType {
        //Here will go the action when clicking one option
    }
}