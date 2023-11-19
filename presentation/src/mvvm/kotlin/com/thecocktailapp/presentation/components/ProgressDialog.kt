package com.thecocktailapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.compose.components.utils.LottieProgressDialog
import com.thecocktailapp.ui.R

@Composable
fun ProgressDialog() {
    LottieProgressDialog(animation = R.raw.loading, height = 260.dp, width = 260.dp)
}