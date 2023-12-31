package com.thecocktailapp.presentation.activities

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mzaragozaserrano.presentation.databinding.CoreDrawerLayoutBinding
import com.mzaragozaserrano.presentation.view.adapters.NavMenuAdapter
import com.thecocktailapp.presentation.base.TheCocktailAppBaseActivity
import com.thecocktailapp.presentation.utils.mvi.CommonViewState
import com.thecocktailapp.presentation.utils.mvi.KotlinIntent
import com.thecocktailapp.presentation.utils.mvi.KotlinViewState
import com.thecocktailapp.presentation.viewmodels.KotlinViewModel
import com.thecocktailapp.presentation.vo.MenuItem
import com.thecocktailapp.presentation.vo.getMenuOptions
import com.thecocktailapp.ui.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class KotlinActivity :
    TheCocktailAppBaseActivity<KotlinViewState, KotlinIntent, CoreDrawerLayoutBinding, KotlinViewModel>() {

    override val binding by lazy { CoreDrawerLayoutBinding.inflate(layoutInflater) }
    override val viewModel: KotlinViewModel by viewModels()

    val drawerLayout: DrawerLayout by lazy { binding.drawerLayout }

    private lateinit var navController: NavController

    private val adapter: NavMenuAdapter<MenuItem> by lazy {
        NavMenuAdapter(
            onBindItem = { binding, item ->
                with(binding) {
                    icMenu.apply {
                        setColorFilter(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.color_primary
                            )
                        )
                        setImageDrawable(ContextCompat.getDrawable(applicationContext, item.icon))
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

    override fun CoreDrawerLayoutBinding.setUpView() {
        setUpAdapter()
        setUpNavHostFragment()
    }

    private fun CoreDrawerLayoutBinding.setUpNavHostFragment() {
        navView.setBackgroundResource(R.drawable.background_nav_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(navHostContainer.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun CoreDrawerLayoutBinding.setUpAdapter() {
        listMenu.adapter = adapter
        adapter.list = getMenuOptions()
    }

    override fun renderView(state: KotlinViewState) {
        when (state) {
            is CommonViewState.Idle -> {}
            is CommonViewState.Initialized -> {
                showRandomDrink()
            }

            is KotlinViewState.Navigate.ToHomeFragment -> {
                startNavigationFrom(R.id.HomeFragment)
            }

            is KotlinViewState.Navigate.ToSplashFragment -> {
                startNavigationFrom(R.id.SplashFragment)
            }
        }
    }

    private fun showRandomDrink() {
        emitAction(KotlinIntent.ShowRandomDrink)
    }

    private fun startNavigationFrom(destination: Int) {
        navController.graph =
            navController.navInflater.inflate(R.navigation.nav_kotlin_graph).apply {
                setStartDestination(destination)
            }
    }

}