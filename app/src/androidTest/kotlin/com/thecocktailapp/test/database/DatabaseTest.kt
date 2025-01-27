/*
package com.thecocktailapp.test.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.thecocktailapp.data.datasources.local.database.FavoritesDataSource
import com.thecocktailapp.data.datasources.local.database.FavoritesDatabase
import com.thecocktailapp.data.dto.DrinkDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class DatabaseTest {

    private lateinit var favoritesDatabase: FavoritesDatabase
    private lateinit var favoritesDataSource: FavoritesDataSource

    @Before
    fun setupDatabase() {
        favoritesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoritesDatabase::class.java
        ).allowMainThreadQueries().build()

        favoritesDataSource = favoritesDatabase.favoritesDataSource()
    }

    @After
    fun closeDatabase() {
        favoritesDatabase.close()
    }

    @Test
    fun when_inserting_drink_returns_true(): Unit = runBlocking {
        val drink = DrinkDTO(
            dateModified = "2015-09-03 03:09:44",
            idDrink = "15813",
            strAlcoholic = "Alcoholic",
            strCategory = "Coffee / Tea",
            strCreativeCommonsConfirmed = "No",
            strDrink = "Herbal flame",
            strDrinkAlternate = null,
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/rrstxv1441246184.jpg",
            strGlass = "Mason jar",
            strIBA = null,
            strImageAttribution = null,
            strImageSource = null,
            strIngredient1 = "Hot Damn",
            strIngredient2 = "Tea",
            strIngredient3 = null,
            strIngredient4 = null,
            strIngredient5 = null,
            strIngredient6 = null,
            strIngredient7 = null,
            strIngredient8 = null,
            strIngredient9 = null,
            strIngredient10 = null,
            strIngredient11 = null,
            strIngredient12 = null,
            strIngredient13 = null,
            strIngredient14 = null,
            strIngredient15 = null,
            strInstructions = "Pour Hot Damn 100 in bottom of a jar or regular glass.Fill the rest of the glass with sweet tea.Stir with spoon, straw, or better yet a cinnamon stick and leave it in .",
            strInstructionsDE = "Gießen Sie Hot Damn 100 auf den Boden eines normalen Glases.Füllen Sie den Rest des Glases mit süßem Tee.Mit einem Löffel, Strohhalm oder besser noch einer Zimtstange umrühren und darin belassen .",
            strInstructionsES = null,
            strInstructionsFR = null,
            strInstructionsIT = "Versa Hot Damn 100 sul fondo di un barattolo o di un bicchiere normale.Riempi il resto del bicchiere con tè dolce . Mescola con un cucchiaio, una cannuccia o, meglio ancora, una stecca di cannella e lasciala dentro.",
            strMeasure1 = "5 shots",
            strMeasure2 = "very sweet",
            strMeasure3 = null,
            strMeasure4 = null,
            strMeasure5 = null,
            strMeasure6 = null,
            strMeasure7 = null,
            strMeasure8 = null,
            strMeasure9 = null,
            strMeasure10 = null,
            strMeasure11 = null,
            strMeasure12 = null,
            strMeasure13 = null,
            strMeasure14 = null,
            strMeasure15 = null,
            strInstructionsZHHANS = null,
            strInstructionsZHHANT = null,
            strTags = null,
            strVideo = null
        )
        favoritesDataSource.addDrink(drink = drink)

        val job = async(Dispatchers.IO) {
            val list = favoritesDataSource.getAllFavorites()
            assert(list.contains(drink))
        }
        job.cancelAndJoin()
    }

    @Test
    fun when_removing_drink_returns_true(): Unit = runBlocking {
        val drink = DrinkDTO(
            dateModified = "2015-09-03 03:09:44",
            idDrink = "15813",
            strAlcoholic = "Alcoholic",
            strCategory = "Coffee / Tea",
            strCreativeCommonsConfirmed = "No",
            strDrink = "Herbal flame",
            strDrinkAlternate = null,
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/rrstxv1441246184.jpg",
            strGlass = "Mason jar",
            strIBA = null,
            strImageAttribution = null,
            strImageSource = null,
            strIngredient1 = "Hot Damn",
            strIngredient2 = "Tea",
            strIngredient3 = null,
            strIngredient4 = null,
            strIngredient5 = null,
            strIngredient6 = null,
            strIngredient7 = null,
            strIngredient8 = null,
            strIngredient9 = null,
            strIngredient10 = null,
            strIngredient11 = null,
            strIngredient12 = null,
            strIngredient13 = null,
            strIngredient14 = null,
            strIngredient15 = null,
            strInstructions = "Pour Hot Damn 100 in bottom of a jar or regular glass.Fill the rest of the glass with sweet tea.Stir with spoon, straw, or better yet a cinnamon stick and leave it in .",
            strInstructionsDE = "Gießen Sie Hot Damn 100 auf den Boden eines normalen Glases.Füllen Sie den Rest des Glases mit süßem Tee.Mit einem Löffel, Strohhalm oder besser noch einer Zimtstange umrühren und darin belassen .",
            strInstructionsES = null,
            strInstructionsFR = null,
            strInstructionsIT = "Versa Hot Damn 100 sul fondo di un barattolo o di un bicchiere normale.Riempi il resto del bicchiere con tè dolce . Mescola con un cucchiaio, una cannuccia o, meglio ancora, una stecca di cannella e lasciala dentro.",
            strMeasure1 = "5 shots",
            strMeasure2 = "very sweet",
            strMeasure3 = null,
            strMeasure4 = null,
            strMeasure5 = null,
            strMeasure6 = null,
            strMeasure7 = null,
            strMeasure8 = null,
            strMeasure9 = null,
            strMeasure10 = null,
            strMeasure11 = null,
            strMeasure12 = null,
            strMeasure13 = null,
            strMeasure14 = null,
            strMeasure15 = null,
            strInstructionsZHHANS = null,
            strInstructionsZHHANT = null,
            strTags = null,
            strVideo = null
        )

        favoritesDataSource.addDrink(drink = drink)
        favoritesDataSource.removeDrink(drink = drink)

        val job = async(Dispatchers.IO) {
            val list = favoritesDataSource.getAllFavorites()
            assert(list.contains(drink).not())
        }
        job.cancelAndJoin()
    }

    @Test
    fun when_removing_drink_returns_false(): Unit = runBlocking {
        val firstDrink = DrinkDTO(
            dateModified = "2015-09-03 03:09:44",
            idDrink = "15813",
            strAlcoholic = "Alcoholic",
            strCategory = "Coffee / Tea",
            strCreativeCommonsConfirmed = "No",
            strDrink = "Herbal flame",
            strDrinkAlternate = null,
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/rrstxv1441246184.jpg",
            strGlass = "Mason jar",
            strIBA = null,
            strImageAttribution = null,
            strImageSource = null,
            strIngredient1 = "Hot Damn",
            strIngredient2 = "Tea",
            strIngredient3 = null,
            strIngredient4 = null,
            strIngredient5 = null,
            strIngredient6 = null,
            strIngredient7 = null,
            strIngredient8 = null,
            strIngredient9 = null,
            strIngredient10 = null,
            strIngredient11 = null,
            strIngredient12 = null,
            strIngredient13 = null,
            strIngredient14 = null,
            strIngredient15 = null,
            strInstructions = "Pour Hot Damn 100 in bottom of a jar or regular glass.Fill the rest of the glass with sweet tea.Stir with spoon, straw, or better yet a cinnamon stick and leave it in .",
            strInstructionsDE = "Gießen Sie Hot Damn 100 auf den Boden eines normalen Glases.Füllen Sie den Rest des Glases mit süßem Tee.Mit einem Löffel, Strohhalm oder besser noch einer Zimtstange umrühren und darin belassen .",
            strInstructionsES = null,
            strInstructionsFR = null,
            strInstructionsIT = "Versa Hot Damn 100 sul fondo di un barattolo o di un bicchiere normale.Riempi il resto del bicchiere con tè dolce . Mescola con un cucchiaio, una cannuccia o, meglio ancora, una stecca di cannella e lasciala dentro.",
            strMeasure1 = "5 shots",
            strMeasure2 = "very sweet",
            strMeasure3 = null,
            strMeasure4 = null,
            strMeasure5 = null,
            strMeasure6 = null,
            strMeasure7 = null,
            strMeasure8 = null,
            strMeasure9 = null,
            strMeasure10 = null,
            strMeasure11 = null,
            strMeasure12 = null,
            strMeasure13 = null,
            strMeasure14 = null,
            strMeasure15 = null,
            strInstructionsZHHANS = null,
            strInstructionsZHHANT = null,
            strTags = null,
            strVideo = null
        )

        val secondDrink = DrinkDTO(
            dateModified = "2015-09-03 03:09:44",
            idDrink = "65412",
            strAlcoholic = "Alcoholic",
            strCategory = "Coffee / Tea",
            strCreativeCommonsConfirmed = "No",
            strDrink = "Herbal flame",
            strDrinkAlternate = null,
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/rrstxv1441246184.jpg",
            strGlass = "Mason jar",
            strIBA = null,
            strImageAttribution = null,
            strImageSource = null,
            strIngredient1 = "Hot Damn",
            strIngredient2 = "Tea",
            strIngredient3 = null,
            strIngredient4 = null,
            strIngredient5 = null,
            strIngredient6 = null,
            strIngredient7 = null,
            strIngredient8 = null,
            strIngredient9 = null,
            strIngredient10 = null,
            strIngredient11 = null,
            strIngredient12 = null,
            strIngredient13 = null,
            strIngredient14 = null,
            strIngredient15 = null,
            strInstructions = "Pour Hot Damn 100 in bottom of a jar or regular glass.Fill the rest of the glass with sweet tea.Stir with spoon, straw, or better yet a cinnamon stick and leave it in .",
            strInstructionsDE = "Gießen Sie Hot Damn 100 auf den Boden eines normalen Glases.Füllen Sie den Rest des Glases mit süßem Tee.Mit einem Löffel, Strohhalm oder besser noch einer Zimtstange umrühren und darin belassen .",
            strInstructionsES = null,
            strInstructionsFR = null,
            strInstructionsIT = "Versa Hot Damn 100 sul fondo di un barattolo o di un bicchiere normale.Riempi il resto del bicchiere con tè dolce . Mescola con un cucchiaio, una cannuccia o, meglio ancora, una stecca di cannella e lasciala dentro.",
            strMeasure1 = "5 shots",
            strMeasure2 = "very sweet",
            strMeasure3 = null,
            strMeasure4 = null,
            strMeasure5 = null,
            strMeasure6 = null,
            strMeasure7 = null,
            strMeasure8 = null,
            strMeasure9 = null,
            strMeasure10 = null,
            strMeasure11 = null,
            strMeasure12 = null,
            strMeasure13 = null,
            strMeasure14 = null,
            strMeasure15 = null,
            strInstructionsZHHANS = null,
            strInstructionsZHHANT = null,
            strTags = null,
            strVideo = null
        )

        favoritesDataSource.addDrink(drink = firstDrink)
        favoritesDataSource.removeDrink(drink = secondDrink)

        val job = async(Dispatchers.IO) {
            val list = favoritesDataSource.getAllFavorites()
            assert(list.contains(firstDrink))
        }
        job.cancelAndJoin()
    }

}*/
