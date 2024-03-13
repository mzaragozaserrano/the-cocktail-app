package viewmodels

import app.cash.turbine.test
import com.thecocktailapp.datasources.FakeCocktailDataSourceImpl
import com.thecocktailapp.datasources.FakePreferencesDataSourceImpl
import com.thecocktailapp.datasources.FakeResourcesDataSourceImpl
import com.thecocktailapp.presentation.viewmodels.home.HomeViewModel
import com.thecocktailapp.presentation.vo.DrinkType
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.ErrorVO
import com.thecocktailapp.repositories.FakeCocktailRepositoryImpl
import com.thecocktailapp.repositories.FakeNetworkRepositoryImpl
import com.thecocktailapp.usecases.FakeGetDrinksByTypeUseCaseImpl
import com.thecocktailapp.utils.MainDispatcherRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runners.model.Statement
import org.mockito.InjectMocks
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class HomeViewModelTest {

    private lateinit var cocktailRepository: FakeCocktailRepositoryImpl
    private lateinit var getDrinksByType: FakeGetDrinksByTypeUseCaseImpl

    @InjectMocks
    private lateinit var networkRepository: FakeNetworkRepositoryImpl

    @InjectMocks
    private lateinit var cocktailDataSource: FakeCocktailDataSourceImpl

    @InjectMocks
    private lateinit var resourcesDataSource: FakeResourcesDataSourceImpl

    @InjectMocks
    private lateinit var preferencesDataSource: FakePreferencesDataSourceImpl

    @Inject
    lateinit var viewModel: HomeViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val setupRule = TestRule { base, description ->
        object : Statement() {
            override fun evaluate() {
                val testName = description.methodName
                when {
                    testName.contains("(non alcoholic)") -> setUpSuccessNoneTest()
                    testName.contains("(optional alcohol)") -> setUpSuccessOptionalTest()
                    testName.contains("success result") -> setUpSuccessTest()
                    testName.contains("data not found") -> setUpDataSourceErrorTest()
                    testName.contains("connectivity error") -> setUpUseCaseErrorTest()
                    else -> setup()
                }
                base.evaluate()
            }
        }
    }

    private fun setUpSuccessTest() {
        Dispatchers.setMain(mainDispatcherRule.testDispatcher)
        MockitoAnnotations.openMocks(this)
        cocktailDataSource.setResult(hasError = false)
        cocktailRepository = FakeCocktailRepositoryImpl(
            cocktailDataSource,
            preferencesDataSource,
            resourcesDataSource
        )
        setUpViewModel(isConnected = true)
    }

    private fun setUpSuccessNoneTest() {
        Dispatchers.setMain(mainDispatcherRule.testDispatcher)
        MockitoAnnotations.openMocks(this)
        cocktailDataSource.setResult(hasError = false)
        cocktailRepository = FakeCocktailRepositoryImpl(
            cocktailDataSource,
            preferencesDataSource,
            resourcesDataSource
        )
        setUpViewModel(isConnected = true, dbId = 1)
    }

    private fun setUpSuccessOptionalTest() {
        Dispatchers.setMain(mainDispatcherRule.testDispatcher)
        MockitoAnnotations.openMocks(this)
        cocktailDataSource.setResult(hasError = false)
        cocktailRepository = FakeCocktailRepositoryImpl(
            cocktailDataSource,
            preferencesDataSource,
            resourcesDataSource
        )
        setUpViewModel(isConnected = true, dbId = 2)
    }

    private fun setUpDataSourceErrorTest() {
        Dispatchers.setMain(mainDispatcherRule.testDispatcher)
        MockitoAnnotations.openMocks(this)
        cocktailDataSource.setResult(hasError = true)
        cocktailRepository = FakeCocktailRepositoryImpl(
            cocktailDataSource,
            preferencesDataSource,
            resourcesDataSource
        )
        setUpViewModel(isConnected = true)
    }

    private fun setUpUseCaseErrorTest() {
        Dispatchers.setMain(mainDispatcherRule.testDispatcher)
        MockitoAnnotations.openMocks(this)
        cocktailRepository = FakeCocktailRepositoryImpl(
            cocktailDataSource,
            preferencesDataSource,
            resourcesDataSource
        )
        setUpViewModel(isConnected = false)
    }

    fun setup() {
        Dispatchers.setMain(mainDispatcherRule.testDispatcher)
        MockitoAnnotations.openMocks(this)
        cocktailRepository = FakeCocktailRepositoryImpl(
            cocktailDataSource,
            preferencesDataSource,
            resourcesDataSource
        )
    }

    @Test
    fun `when getDrinkByTypeUseCase (alcoholic) is invoked, loading result is emitted first, followed by a success result`() =
        runTest {
            viewModel.state.test {
                assertEquals(HomeViewModel.HomeUiState.Loading, awaitItem())
                assertEquals(
                    HomeViewModel.HomeUiState.Success(
                        listOf(
                            DrinkVO(
                                category = "Coffee / Tea",
                                dateModified = "2015-09-03 03:09:44",
                                drinkType = DrinkType.Alcoholic,
                                glass = "Mason jar",
                                id = "15813",
                                name = "Herbal flame",
                                ingredients = listOf(
                                    "Hot Damn - 5 shots",
                                    "Tea - very sweet"
                                ),
                                instructions = "Pour Hot Damn 100 in bottom of a jar or regular glass.Fill the rest of the glass with sweet tea.Stir with spoon, straw, or better yet a cinnamon stick and leave it in .",
                                urlImage = "https://www.thecocktaildb.com/images/media/drink/rrstxv1441246184.jpg"
                            )
                        )
                    ),
                    awaitItem()
                )
            }
        }

    @Test
    fun `when getDrinkByTypeUseCase (non alcoholic) is invoked, loading result is emitted first, followed by a success result`() =
        runTest {
            viewModel.state.test {
                assertEquals(HomeViewModel.HomeUiState.Loading, awaitItem())
                assertEquals(
                    HomeViewModel.HomeUiState.Success(
                        listOf(
                            DrinkVO(
                                category = "Cocktail",
                                dateModified = "2016-07-18 22:07:32",
                                drinkType = DrinkType.None,
                                glass = "Highball Glass",
                                id = "12560",
                                name = "Afterglow",
                                ingredients = listOf(
                                    "Grenadine - 1 part",
                                    "Orange juice - 4 part",
                                    "Pineapple juice - 4 part"
                                ),
                                instructions = "Mix. Serve over ice.",
                                urlImage = "https://www.thecocktaildb.com/images/media/drink/vuquyv1468876052.jpg"
                            )
                        )
                    ),
                    awaitItem()
                )
            }
        }

    @Test
    fun `when getDrinkByTypeUseCase (optional alcohol) is invoked, loading result is emitted first, followed by a success result`() =
        runTest {
            viewModel.state.test {
                assertEquals(HomeViewModel.HomeUiState.Loading, awaitItem())
                assertEquals(
                    HomeViewModel.HomeUiState.Success(
                        listOf(
                            DrinkVO(
                                category = "Punch / Party Drink",
                                dateModified = "2016-02-03 15:26:58",
                                drinkType = DrinkType.Optional,
                                glass = "Collins Glass",
                                id = "12864",
                                name = "Apple Cider Punch",
                                ingredients = listOf(
                                    "Apple cider - 4 qt",
                                    "Brown sugar - 1 cup",
                                    "Lemonade - 6 oz frozen",
                                    "Orange juice - 6 oz frozen",
                                    "Cloves - 6 whole",
                                    "Allspice - 6 whole",
                                    "Nutmeg - 1 tsp ground",
                                    "Cinnamon - 3 sticks"
                                ),
                                instructions = "If you use the whole all spice and cloves, tie them in cheesecloth. Heat the mixture. Stir occasionally. If you want an alcoholic drink, rum would be nice.",
                                urlImage = "https://www.thecocktaildb.com/images/media/drink/xrqxuv1454513218.jpg"
                            )
                        )
                    ),
                    awaitItem()
                )
            }
        }

    @Test
    fun `when getDrinkByTypeUseCase is invoked, loading result is emitted first, followed by a data not found error result`() =
        runTest {
            viewModel.state.test {
                assertEquals(
                    HomeViewModel.HomeUiState.Error(ErrorVO.DataNotFound),
                    awaitItem()
                )
            }
        }

    @Test
    fun `when getDrinkByTypeUseCase is invoked but a connectivity error result is emitted first`() =
        runTest {
            viewModel.state.test {
                assertEquals(
                    HomeViewModel.HomeUiState.Error(ErrorVO.Connectivity),
                    awaitItem()
                )
            }
        }

    private fun setUpViewModel(isConnected: Boolean, dbId: Int = 0) {
        networkRepository.setConnectionStatus(isConnected = isConnected)
        getDrinksByType =
            FakeGetDrinksByTypeUseCaseImpl(cocktailRepository, networkRepository)
        getDrinksByType.setAlcoholicType(dbId = dbId)
        viewModel = HomeViewModel(getDrinksByType)
    }

}
