package com.thecocktailapp.presentation.components.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.images.LottieImage
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.utils.PROGRESS_DIALOG_LOTTIE

@Composable
fun ProgressDialog() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieImage(
            modifier = Modifier
                .height(height = 260.dp)
                .width(width = 260.dp)
                .testTag(tag = PROGRESS_DIALOG_LOTTIE),
            animationId = R.raw.loading
        )
    }
}

@Preview
@Composable
private fun ProgressDialogPrev() {
    ProgressDialog()
}