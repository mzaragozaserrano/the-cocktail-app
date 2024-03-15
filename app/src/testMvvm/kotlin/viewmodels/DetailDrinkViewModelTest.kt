package viewmodels

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.thecocktailapp.datasources.FakeCocktailDataSourceImpl
import com.thecocktailapp.datasources.FakeFavoritesDataSourceImpl
import com.thecocktailapp.datasources.FakePreferencesDataSourceImpl
import com.thecocktailapp.datasources.FakeResourcesDataSourceImpl
import com.thecocktailapp.presentation.utils.navigation.NavArg
import com.thecocktailapp.presentation.viewmodels.detail.DetailDrinkViewModel
import com.thecocktailapp.presentation.vo.DrinkType
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.ErrorVO
import com.thecocktailapp.repositories.FakeCocktailRepositoryImpl
import com.thecocktailapp.repositories.FakeFavoritesRepositoryImpl
import com.thecocktailapp.repositories.FakeNetworkRepositoryImpl
import com.thecocktailapp.usecases.FakeAddFavoriteDrinkUseCaseImpl
import com.thecocktailapp.usecases.FakeGetDrinkByIdUseCaseImpl
import com.thecocktailapp.usecases.FakeIsFavoriteDrinkUseCaseImpl
import com.thecocktailapp.usecases.FakeRemoveFavoriteDrinkUseCaseImpl
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
class DetailDrinkViewModelTest {

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var cocktailRepository: FakeCocktailRepositoryImpl
    private lateinit var favoritesRepository: FakeFavoritesRepositoryImpl

    private lateinit var addFavoriteDrink: FakeAddFavoriteDrinkUseCaseImpl
    private lateinit var getDrinkById: FakeGetDrinkByIdUseCaseImpl
    private lateinit var isFavoriteDrink: FakeIsFavoriteDrinkUseCaseImpl
    private lateinit var removeFavoriteDrink: FakeRemoveFavoriteDrinkUseCaseImpl

    @InjectMocks
    private lateinit var networkRepository: FakeNetworkRepositoryImpl

    @InjectMocks
    private lateinit var cocktailDataSource: FakeCocktailDataSourceImpl

    @InjectMocks
    private lateinit var favoritesDataSource: FakeFavoritesDataSourceImpl

    @InjectMocks
    private lateinit var resourcesDataSource: FakeResourcesDataSourceImpl

    @InjectMocks
    private lateinit var preferencesDataSource: FakePreferencesDataSourceImpl

    @Inject
    lateinit var viewModel: DetailDrinkViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val setupRule = TestRule { base, description ->
        object : Statement() {
            override fun evaluate() {
                val testName = description.methodName
                when {
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
            cocktailDataSource = cocktailDataSource,
            preferencesDataSource = preferencesDataSource,
            resourcesDataSource = resourcesDataSource
        )
        favoritesRepository = FakeFavoritesRepositoryImpl(
            favoritesDataSource = favoritesDataSource,
            resourcesDataSource = resourcesDataSource
        )
        setUpViewModel(isConnected = true)
    }

    private fun setUpDataSourceErrorTest() {
        Dispatchers.setMain(mainDispatcherRule.testDispatcher)
        MockitoAnnotations.openMocks(this)
        cocktailDataSource.setResult(hasError = true)
        cocktailRepository = FakeCocktailRepositoryImpl(
            cocktailDataSource = cocktailDataSource,
            preferencesDataSource = preferencesDataSource,
            resourcesDataSource = resourcesDataSource
        )
        favoritesRepository = FakeFavoritesRepositoryImpl(
            favoritesDataSource = favoritesDataSource,
            resourcesDataSource = resourcesDataSource
        )
        setUpViewModel(isConnected = true)
    }

    private fun setUpUseCaseErrorTest() {
        Dispatchers.setMain(mainDispatcherRule.testDispatcher)
        MockitoAnnotations.openMocks(this)
        cocktailRepository = FakeCocktailRepositoryImpl(
            cocktailDataSource = cocktailDataSource,
            preferencesDataSource = preferencesDataSource,
            resourcesDataSource = resourcesDataSource
        )
        favoritesRepository = FakeFavoritesRepositoryImpl(
            favoritesDataSource = favoritesDataSource,
            resourcesDataSource = resourcesDataSource
        )
        setUpViewModel(isConnected = false)
    }

    fun setup() {
        Dispatchers.setMain(mainDispatcherRule.testDispatcher)
        MockitoAnnotations.openMocks(this)
        cocktailRepository = FakeCocktailRepositoryImpl(
            cocktailDataSource = cocktailDataSource,
            preferencesDataSource = preferencesDataSource,
            resourcesDataSource = resourcesDataSource
        )
        favoritesRepository = FakeFavoritesRepositoryImpl(
            favoritesDataSource = favoritesDataSource,
            resourcesDataSource = resourcesDataSource
        )
    }

    @Test
    fun `when getDrinkByIdUseCase is invoked, loading result is emitted first, followed by a success result`() =
        runTest {
            viewModel.state.test {
                assertEquals(DetailDrinkViewModel.DetailDrinkUiState.Loading, awaitItem())
                assertEquals(
                    DetailDrinkViewModel.DetailDrinkUiState.Success(
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
                    ),
                    awaitItem()
                )
            }
        }


    @Test
    fun `when getDrinkByIdUseCase is invoked, loading result is emitted first, followed by a data not found error result`() =
        runTest {
            viewModel.state.test {
                assertEquals(
                    DetailDrinkViewModel.DetailDrinkUiState.Error(ErrorVO.DataNotFound),
                    awaitItem()
                )
            }
        }

    @Test
    fun `when getDrinkByIdUseCase is invoked but a connectivity error result is emitted first`() =
        runTest {
            viewModel.state.test {
                assertEquals(
                    DetailDrinkViewModel.DetailDrinkUiState.Error(ErrorVO.Connectivity),
                    awaitItem()
                )
            }
        }

    private fun setUpViewModel(isConnected: Boolean) {
        savedStateHandle = SavedStateHandle()
        savedStateHandle[NavArg.DrinkId.key] = "15813"
        networkRepository.setConnectionStatus(isConnected = isConnected)
        addFavoriteDrink =
            FakeAddFavoriteDrinkUseCaseImpl(favoritesRepository = favoritesRepository)
        getDrinkById = FakeGetDrinkByIdUseCaseImpl(
            cocktailRepository = cocktailRepository,
            networkRepository = networkRepository
        )
        isFavoriteDrink =
            FakeIsFavoriteDrinkUseCaseImpl(favoritesRepository = favoritesRepository)
        removeFavoriteDrink =
            FakeRemoveFavoriteDrinkUseCaseImpl(favoritesRepository = favoritesRepository)
        viewModel = DetailDrinkViewModel(
            addFavoriteDrink = addFavoriteDrink,
            getDrinkById = getDrinkById,
            isFavoriteDrink = isFavoriteDrink,
            removeFavoriteDrink = removeFavoriteDrink,
            savedStateHandle = savedStateHandle
        )
    }

}
