package com.thecocktailapp.presentation.alerts

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.thecocktailapp.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LottieProgressDialog(@RawRes animation: Int = R.raw.loading) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))
    AlertDialog(
        modifier = Modifier
            .width(260.dp)
            .height(260.dp),
        onDismissRequest = {}
    ) {
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
}

@Preview
@Composable
private fun LottieProgressDialogPrev() {
    LottieProgressDialog()
}