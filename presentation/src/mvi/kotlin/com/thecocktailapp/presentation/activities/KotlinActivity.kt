package com.thecocktailapp.presentation.activities

import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mzs.core.databinding.CoreDrawerLayoutBinding
import com.mzs.core.databinding.CoreItemMenuBinding
import com.mzs.core.presentation.components.view.NavMenuAdapter
import com.thecocktailapp.presentation.R
import com.thecocktailapp.presentation.base.TheCocktailAppBaseActivity
import com.thecocktailapp.presentation.utils.CommonViewState
import com.thecocktailapp.presentation.utils.KotlinAction
import com.thecocktailapp.presentation.utils.KotlinIntent
import com.thecocktailapp.presentation.utils.KotlinResult
import com.thecocktailapp.presentation.utils.KotlinViewState
import com.thecocktailapp.presentation.viewmodels.KotlinViewModel
import com.thecocktailapp.presentation.vo.getMenuOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class KotlinActivity :
    TheCocktailAppBaseActivity<KotlinViewState, KotlinIntent, KotlinAction, KotlinResult, CoreDrawerLayoutBinding, KotlinViewModel>() {

    override val binding by lazy { CoreDrawerLayoutBinding.inflate(layoutInflater) }
    override val viewModel: KotlinViewModel by viewModel()

    val drawerLayout: DrawerLayout by lazy { binding.drawerLayout }

    private lateinit var navController: NavController

    private val adapter: NavMenuAdapter<CoreItemMenuBinding> by lazy {
        NavMenuAdapter(
            bindingInflater = CoreItemMenuBinding::inflate,
            onBindItem = { item, binding ->
                with(binding) {
                    icMenu.apply {
                        setColorFilter(
                            ContextCompat.getColor(
                                this@KotlinActivity,
                                R.color.color_primary
                            )
                        )
                        setImageDrawable(
                            ContextCompat.getDrawable(
                                this@KotlinActivity,
                                item.iconId
                            )
                        )
                    }
                    textViewMenu.apply {
                        text = ContextCompat.getString(this@KotlinActivity, item.titleId)
                        setTextColor(
                            ContextCompat.getColor(
                                this@KotlinActivity,
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
                startNavigationFrom(destination = R.id.HomeFragment)
            }

            is KotlinViewState.Navigate.ToSplashFragment -> {
                startNavigationFrom(destination = R.id.SplashFragment)
            }
        }
    }

    private fun showRandomDrink() {
        emitAction(intent = KotlinIntent.ShowRandomDrink)
    }

    private fun startNavigationFrom(destination: Int) {
        navController.graph =
            navController.navInflater.inflate(R.navigation.nav_kotlin_graph).apply {
                setStartDestination(destination)
            }
    }

}