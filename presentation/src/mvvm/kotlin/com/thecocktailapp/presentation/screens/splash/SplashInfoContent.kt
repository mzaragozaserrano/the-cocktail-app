package com.thecocktailapp.presentation.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thecocktailapp.presentation.R
import presentation.components.texts.BlinkingText
import presentation.components.texts.ExtraLargeBoldText
import presentation.components.texts.ExtraSmallMediumText

@Composable
fun SplashInfoContent(modifier: Modifier = Modifier, name: String) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(topStart = 48.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 48.dp)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(space = 6.dp)
        ) {
            if (name.isNotEmpty()) {
                ExtraSmallMediumText(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.color_secondary_text_highlight),
                    text = name
                )
                ExtraLargeBoldText(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onBackground,
                    text = stringResource(id = R.string.first_title_splash_fragment).uppercase()
                )
                BlinkingText(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.color_text_highlight),
                    text = stringResource(id = R.string.second_title_splash_fragment).uppercase()
                )
                ExtraLargeBoldText(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onBackground,
                    text = stringResource(id = R.string.third_title_splash_fragment).uppercase()
                )
            }
        }
    }

}