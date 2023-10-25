package com.thecocktailapp.presentation.view.base

import android.content.Intent
import androidx.viewbinding.ViewBinding
import com.mzaragozaserrano.presentation.view.base.BaseActivity
import com.mzaragozaserrano.presentation.view.base.BaseViewModel
import com.thecocktailapp.presentation.view.activities.KotlinActivity

abstract class TheCocktailAppBaseActivity<S, I, VB : ViewBinding, VM : BaseViewModel<S, I>> :
    BaseActivity<S, I, VB, VM>() {

    fun navigateToKotlinModule() {
        val intent = Intent(this, KotlinActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}