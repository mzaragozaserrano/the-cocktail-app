package com.thecocktailapp.presentation.components.utils

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.presentation.compose.components.utils.LottieProgressDialog
import com.thecocktailapp.presentation.R

@Composable
fun ProgressDialog() {
    LottieProgressDialog(
        modifier = Modifier
            .height(height = 260.dp)
            .width(width = 260.dp),
        animation = R.raw.loading
    )
}