package com.thecocktailapp.presentation.view.base

import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.mzaragozaserrano.presentation.view.base.BaseFragment
import com.mzaragozaserrano.presentation.view.base.MVIViewModel
import com.thecocktailapp.presentation.view.activities.HomeActivity

abstract class TheCocktailAppBaseFragment<S, I, VB : ViewBinding, VM : MVIViewModel<S, I>>(
    @LayoutRes override val layout: Int,
    private val allowGoBack: Boolean,
) : BaseFragment<S, I, VB, VM>(layout) {

    override fun onBackPressed() {
        if (allowGoBack) {
            (activity as TheCocktailAppBaseActivity<*, *, *, *>).clearAndNavigateToNewActivity(
                HomeActivity::class.java
            )
        }
    }

}