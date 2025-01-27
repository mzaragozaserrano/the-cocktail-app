package com.thecocktailapp.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.texts.BlinkingText
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.components.buttons.DualActionButton

@Composable
fun SplashInfoContent(name: String, onSeeClicked: () -> Unit, onCancelClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 48.dp)
            .verticalScroll(state = rememberScrollState()),
        content = {
            Column(
                modifier = Modifier.weight(weight = 1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(space = 6.dp),
                content = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        color = colorResource(id = R.color.color_secondary_text_highlight),
                        text = name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        text = stringResource(id = R.string.first_title_splash_fragment).uppercase(),
                        style = MaterialTheme.typography.titleLarge
                    )
                    BlinkingText(
                        modifier = Modifier.fillMaxWidth(),
                        color = colorResource(id = R.color.color_text_highlight),
                        fontWeight = FontWeight.Black,
                        text = stringResource(id = R.string.second_title_splash_fragment).uppercase(),
                        textStyle = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        text = stringResource(id = R.string.third_title_splash_fragment).uppercase(),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
            DualActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(bottom = 24.dp),
                buttonBackgroundColor = MaterialTheme.colorScheme.primary,
                buttonText = stringResource(R.string.see_button),
                buttonTextColor = MaterialTheme.colorScheme.onPrimary,
                text = stringResource(R.string.cancel_button),
                onPositiveButtonClicked = onSeeClicked,
                onNegativeButtonClicked = onCancelClicked
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun SplashInfoContentPrev() {
    SplashInfoContent(
        name = "This is a trial text",
        onSeeClicked = { /*Here will go the action when clicking on the main button */ },
        onCancelClicked = { /*Here will go the action when clicking on the secondary button */ }
    )
}