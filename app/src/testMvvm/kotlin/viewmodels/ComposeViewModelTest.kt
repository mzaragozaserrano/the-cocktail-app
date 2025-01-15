package viewmodels

/*import app.cash.turbine.test
import com.thecocktailapp.datasources.FakeCocktailDataSourceImpl
import com.thecocktailapp.datasources.FakePreferencesDataSourceImpl
import com.thecocktailapp.datasources.FakeResourcesDataSourceImpl
import com.thecocktailapp.presentation.viewmodels.ComposeViewModel
import com.thecocktailapp.repositories.FakeCocktailRepositoryImpl
import com.thecocktailapp.usecases.FakeShowRandomDrinkUseCaseImpl
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
import javax.inject.Inject*/

/*
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class ComposeViewModelTest {

    private lateinit var cocktailRepository: FakeCocktailRepositoryImpl
    private lateinit var showRandomDrink: FakeShowRandomDrinkUseCaseImpl

    @InjectMocks
    private lateinit var cocktailDataSource: FakeCocktailDataSourceImpl

    @InjectMocks
    private lateinit var resourcesDataSource: FakeResourcesDataSourceImpl

    @InjectMocks
    private lateinit var preferencesDataSource: FakePreferencesDataSourceImpl

    @Inject
    lateinit var viewModel: ComposeViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val setupRule = TestRule { base, description ->
        object : Statement() {
            override fun evaluate() {
                val testName = description.methodName
                when {
                    testName.contains("success result") -> setUpSuccessTest()
                    testName.contains("error result") -> setUpDataSourceErrorTest()
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
        setUpViewModel(isFirstAccess = true)
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
        setUpViewModel(isFirstAccess = false)
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
    fun `when showRandomDrink is invoked, followed by a success result`() =
        runTest {
            viewModel.state.test {
                assertEquals(ComposeViewModel.ComposeUiState(true), awaitItem())
            }
        }

    @Test
    fun `when showRandomDrink is invoked, followed by a error result`() =
        runTest {
            viewModel.state.test {
                assertEquals(ComposeViewModel.ComposeUiState(false), awaitItem())
            }
        }

    private fun setUpViewModel(isFirstAccess: Boolean) {
        showRandomDrink =
            FakeShowRandomDrinkUseCaseImpl(cocktailRepository = cocktailRepository)
        preferencesDataSource.setIsFirstsAccess(isFirstAccess = isFirstAccess)
        viewModel = ComposeViewModel(showRandomDrink = showRandomDrink)
    }

}
*/
