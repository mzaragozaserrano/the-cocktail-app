import app.cash.turbine.test
import com.thecocktailapp.datasources.FakeCocktailDataSourceImpl
import com.thecocktailapp.datasources.FakePreferencesDataSourceImpl
import com.thecocktailapp.datasources.FakeResourcesDataSourceImpl
import com.thecocktailapp.presentation.utils.CommonViewState
import com.thecocktailapp.presentation.utils.SplashIntent
import com.thecocktailapp.presentation.utils.SplashViewState
import com.thecocktailapp.presentation.viewmodels.SplashViewModel
import com.thecocktailapp.presentation.vo.DrinkType
import com.thecocktailapp.presentation.vo.DrinkVO
import com.thecocktailapp.presentation.vo.ErrorVO
import com.thecocktailapp.repositories.FakeCocktailRepositoryImpl
import com.thecocktailapp.repositories.FakeNetworkRepositoryImpl
import com.thecocktailapp.usecases.FakeGetRandomDrinkUseCaseImpl
import com.thecocktailapp.utils.MainDispatcherRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runners.model.Statement
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class SplashViewModelTest {

    private lateinit var cocktailRepository: FakeCocktailRepositoryImpl
    private lateinit var getRandomDrinkUseCase: FakeGetRandomDrinkUseCaseImpl

    @InjectMocks
    private lateinit var cocktailDataSource: FakeCocktailDataSourceImpl

    @InjectMocks
    private lateinit var preferencesDataSource: FakePreferencesDataSourceImpl

    @InjectMocks
    private lateinit var resourcesDataSource: FakeResourcesDataSourceImpl

    @InjectMocks
    private lateinit var networkRepository: FakeNetworkRepositoryImpl

    @Inject
    lateinit var viewModel: SplashViewModel

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
    }

    @Test
    fun `when getRandomDrinkUseCase is invoked, loading result is emitted first, followed by a success result`() =
        runTest {
            viewModel.mutableViewState.test {
                assertEquals(CommonViewState.Initialized(), awaitItem())
                viewModel.intentFlow.emit(SplashIntent.GetRandomDrink)
                assertEquals(SplashViewState.ShowProgressDialog, awaitItem())
                assertEquals(
                    SplashViewState.SetDrink(
                        DrinkVO(
                            category = "Coffee / Tea",
                            dateModified = "2015-09-03 03:09:44",
                            glass = "Mason jar",
                            id = "15813",
                            drinkType = DrinkType.Alcoholic,
                            name = "Herbal flame",
                            ingredients = listOf(
                                "Hot Damn - 5 shots",
                                "Tea, - very sweet"
                            ),
                            instructions = "Pour Hot Damn 100 in bottom of a jar or regular glass.Fill the rest of the glass with sweet tea.Stir with spoon, straw, or better yet a cinnamon stick and leave it in .",
                            urlImage = "https://www.thecocktaildb.com/images/media/drink/rrstxv1441246184.jpg"
                        )
                    ), awaitItem()
                )
            }
        }


    @Test
    fun `when getRandomDrinkUseCase is invoked, loading result is emitted first, followed by a data not found error result`() =
        runTest {
            viewModel.mutableViewState.test {
                assertEquals(CommonViewState.Initialized(), awaitItem())
                viewModel.intentFlow.emit(SplashIntent.GetRandomDrink)
                assertEquals(SplashViewState.ShowProgressDialog, awaitItem())
                assertEquals(SplashViewState.ShowError(ErrorVO.DataNotFound.messageId), awaitItem())
            }
        }

    @Test
    fun `when getRandomDrinkUseCase is invoked but a connectivity error result is emitted first`() =
        runTest {
            viewModel.mutableViewState.test {
                assertEquals(CommonViewState.Initialized(), awaitItem())
                viewModel.intentFlow.emit(SplashIntent.GetRandomDrink)
                assertEquals(SplashViewState.ShowError(ErrorVO.Connectivity.messageId), awaitItem())
            }
        }

    private fun setUpViewModel(isConnected: Boolean) {
        networkRepository.setConnectionStatus(isConnected = isConnected)
        getRandomDrinkUseCase = FakeGetRandomDrinkUseCaseImpl(cocktailRepository, networkRepository)
        viewModel = SplashViewModel(getRandomDrinkUseCase)
    }

}
