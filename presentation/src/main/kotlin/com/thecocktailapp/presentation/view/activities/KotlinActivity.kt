package com.thecocktailapp.presentation.view.activities

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import com.mzaragozaserrano.presentation.databinding.CoreDrawerLayoutBinding
import com.mzaragozaserrano.presentation.view.adapters.NavMenuAdapter
import com.thecocktailapp.presentation.view.MenuItem
import com.thecocktailapp.presentation.view.base.TheCocktailAppBaseActivity
import com.thecocktailapp.presentation.view.getMenuOptions
import com.thecocktailapp.presentation.view.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.view.utils.mvi.KotlinIntent
import com.thecocktailapp.presentation.view.utils.mvi.KotlinViewState
import com.thecocktailapp.presentation.view.viewmodels.KotlinViewModel
import com.thecocktailapp.ui.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KotlinActivity :
    TheCocktailAppBaseActivity<KotlinViewState, KotlinIntent, CoreDrawerLayoutBinding, KotlinViewModel>() {

    override val binding by lazy { CoreDrawerLayoutBinding.inflate(layoutInflater) }
    override val viewModel: KotlinViewModel by viewModels()

    val drawerLayout: DrawerLayout by lazy { binding.drawerLayout }

    private val adapter: NavMenuAdapter<MenuItem> by lazy {
        NavMenuAdapter(
            onBindItem = { binding, item ->
                with(binding) {
                    icMenu.apply {
                        setImageDrawable(ContextCompat.getDrawable(applicationContext, item.icon))
                        setColorFilter(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.color_primary
                            )
                        )
                    }
                    textViewMenu.apply {
                        text = getString(item.name)
                        setTextColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.color_on_background
                            )
                        )
                    }
                }
            },
            onItemClicked = {

            }
        )
    }

    override fun renderView(state: KotlinViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.SetUpView -> {}
        }
    }

    override fun CoreDrawerLayoutBinding.setUpView() {
        setUpNavHostFragment()
        setUpAdapter()
    }

    private fun CoreDrawerLayoutBinding.setUpNavHostFragment() {
        navView.setBackgroundResource(R.drawable.background_nav_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(navHostContainer.id) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_kotlin_graph)
    }

    private fun CoreDrawerLayoutBinding.setUpAdapter() {
        listMenu.adapter = adapter
        adapter.list = getMenuOptions()
    }

}