package com.thecocktailapp.test.utils

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.thecocktailapp.presentation.utils.PROGRESS_DIALOG_LOTTIE
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun <ActivityClass : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<ActivityClass>, ActivityClass>.isDisplay(
    id: String,
) {
    onNodeWithTag(id).apply {
        assertIsDisplayed()
    }
}

fun <ActivityClass : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<ActivityClass>, ActivityClass>.onClick(
    id: String,
) {
    onNodeWithTag(id).apply {
        assertIsDisplayed()
        performClick()
    }
}

fun <ActivityClass : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<ActivityClass>, ActivityClass>.onScroll(
    id: String,
    index: Int = 1,
) {
    onNodeWithTag(id).apply {
        performScrollToIndex(index)
        performClick()
    }
}

fun <ActivityClass : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<ActivityClass>, ActivityClass>.testProgress() {
    onNodeWithTag(PROGRESS_DIALOG_LOTTIE).assertIsDisplayed()
    runBlocking {
        delay(1000)
    }
    onNodeWithTag(PROGRESS_DIALOG_LOTTIE).assertDoesNotExist()
}