package com.thecocktailapp.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thecocktailapp.presentation.R
import presentation.components.utils.LottieProgressDialog

@Composable
fun ProgressDialog() {
    LottieProgressDialog(
        modifier = Modifier
            .height(height = 260.dp)
            .width(width = 260.dp),
        animation = R.raw.loading
    )
}