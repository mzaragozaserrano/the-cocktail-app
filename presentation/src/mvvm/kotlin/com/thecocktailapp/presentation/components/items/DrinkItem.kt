package com.thecocktailapp.presentation.components.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.presentation.compose.components.buttons.PushedButton
import com.thecocktailapp.core.presentation.compose.components.cards.RoundedEdgeCard
import com.thecocktailapp.core.presentation.compose.components.images.UrlImage
import com.thecocktailapp.core.presentation.compose.components.texts.NormalBoldText
import com.thecocktailapp.core.presentation.utils.conditional
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.utils.HOME_BUTTON_SEE_DETAIL
import com.thecocktailapp.presentation.vo.DrinkType
import com.thecocktailapp.presentation.vo.DrinkVO

@Composable
fun DrinkItem(
    modifier: Modifier = Modifier,
    isFirstItem: Boolean = false,
    item: DrinkVO,
    onButtonClicked: () -> Unit,
) {
    RoundedEdgeCard(
        modifier = modifier,
        borderColor = if (item.isFavorite) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.surface,
        strokeBorder = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            UrlImage(
                modifier = Modifier.padding(all = 8.dp),
                cornerRadius = 8.dp,
                url = item.urlImage
            )
            NormalBoldText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                maxLines = 2,
                minLines = 2,
                text = item.name,
                textAlign = TextAlign.Center
            )
            PushedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .conditional(isFirstItem, { testTag(tag = HOME_BUTTON_SEE_DETAIL) }),
                buttonBackgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimary,
                textId = R.string.see_button,
                textPaddingVertical = 8.dp
            ) {
                onButtonClicked()
            }
        }
    }
}

@Preview
@Composable
private fun DrinkItemPrev() {
    val item = DrinkVO(
        category = "Coffee / Tea",
        dateModified = "2015-09-03 03:09:44",
        drinkType = DrinkType.Alcoholic,
        glass = "Mason jar",
        id = "15813",
        isFavorite = true,
        name = "Herbal flame",
        ingredients = listOf(
            "Hot Damn - 5 shots",
            "Tea - very sweet"
        ),
        instructions = "Pour Hot Damn 100 in bottom of a jar or regular glass.Fill the rest of the glass with sweet tea.Stir with spoon, straw, or better yet a cinnamon stick and leave it in .",
        urlImage = "https://www.thecocktaildb.com/images/media/drink/rrstxv1441246184.jpg"
    )
    DrinkItem(item = item) {

    }
}