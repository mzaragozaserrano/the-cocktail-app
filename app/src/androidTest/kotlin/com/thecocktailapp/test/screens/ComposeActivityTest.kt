package com.thecocktailapp.test.screens


import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.thecocktailapp.presentation.activities.ComposeActivity
import com.thecocktailapp.presentation.utils.DETAIL_TOOLBAR
import com.thecocktailapp.presentation.utils.FAVORITE_TOOLBAR
import com.thecocktailapp.presentation.utils.HOME_BUTTON_SEE_DETAIL
import com.thecocktailapp.presentation.utils.HOME_MENU_BUTTON
import com.thecocktailapp.presentation.utils.HOME_TOOLBAR
import com.thecocktailapp.presentation.utils.MENU_NAVIGATION_ITEM
import com.thecocktailapp.presentation.utils.SPLASH_CANCEL_BUTTON
import com.thecocktailapp.presentation.utils.SPLASH_SEE_BUTTON
import com.thecocktailapp.test.utils.isDisplay
import com.thecocktailapp.test.utils.onClick
import com.thecocktailapp.test.utils.testProgress
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ComposeActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = createAndroidComposeRule<ComposeActivity>()

    @Test
    fun go_from_splash_to_detail() {
        with(mActivityScenarioRule) {
            testProgress()
            onClick(SPLASH_SEE_BUTTON)
            testProgress()
            isDisplay(DETAIL_TOOLBAR)
        }
    }

    @Test
    fun go_from_splash_to_home() {
        with(mActivityScenarioRule) {
            testProgress()
            onClick(SPLASH_CANCEL_BUTTON)
            isDisplay(HOME_TOOLBAR)
        }
    }

    @Test
    fun go_from_home_to_detail() {
        with(mActivityScenarioRule) {
            testProgress()
            onClick(SPLASH_CANCEL_BUTTON)
            isDisplay(HOME_TOOLBAR)
            onClick(HOME_BUTTON_SEE_DETAIL)
            isDisplay(DETAIL_TOOLBAR)
        }
    }

    @Test
    fun go_from_home_to_favorites() {
        with(mActivityScenarioRule) {
            testProgress()
            onClick(SPLASH_CANCEL_BUTTON)
            isDisplay(HOME_TOOLBAR)
            onClick(HOME_MENU_BUTTON)
            onClick(MENU_NAVIGATION_ITEM)
            isDisplay(FAVORITE_TOOLBAR)
        }
    }

}