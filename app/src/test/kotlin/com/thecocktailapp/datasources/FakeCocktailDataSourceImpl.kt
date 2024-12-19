package com.thecocktailapp.datasources

import com.thecocktailapp.data.datasources.services.CocktailDataSource
import com.thecocktailapp.data.dto.CocktailDTO
import com.thecocktailapp.data.dto.DrinkDTO
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.data.dto.ResultDTO
import com.thecocktailapp.data.utils.onError
import com.thecocktailapp.data.utils.onSuccess
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class FakeCocktailDataSourceImpl @Inject constructor() : CocktailDataSource {

    private var hasError: Boolean = true

    fun setResult(hasError: Boolean) {
        this.hasError = hasError
    }

    override suspend fun getDrinkById(id: Int): ResultDTO<CocktailDTO> =
        suspendCancellableCoroutine { continuation ->
            getCocktail(continuation = continuation)
        }

    override suspend fun getDrinksByType(alcoholic: String): ResultDTO<CocktailDTO> =
        suspendCancellableCoroutine { continuation ->
            getCocktail(alcoholic = alcoholic, continuation = continuation)
        }

    override suspend fun getRandomDrink(): ResultDTO<CocktailDTO> =
        suspendCancellableCoroutine { continuation ->
            getCocktail(continuation = continuation)
        }

    private fun getCocktail(
        alcoholic: String = "Alcoholic",
        continuation: CancellableContinuation<ResultDTO<CocktailDTO>>,
    ) {
        if (hasError) {
            continuation.onError(ErrorDTO.DataNotFound)
        } else {
            when (alcoholic) {
                "Alcoholic" -> {
                    continuation.onSuccess(
                        data = CocktailDTO(
                            listOf(
                                DrinkDTO(
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
                                ),
                                DrinkDTO(
                                    idDrink = "14588",
                                    strDrink = "151 Florida Bushwacker",
                                    strDrinkAlternate = null,
                                    strTags = null,
                                    strVideo = null,
                                    strCategory = "Shake",
                                    strIBA = null,
                                    strAlcoholic = "Alcoholic",
                                    strGlass = "Beer mug",
                                    strInstructions = "Combine all ingredients . Blend until smooth . Garnish with chocolate shavings if desired.",
                                    strInstructionsES = null,
                                    strInstructionsDE = "Alle Zutaten vermengen . Mischen, bis alles glatt ist. Auf Wunsch mit Schokoladenraspeln garnieren.",
                                    strInstructionsFR = null,
                                    strInstructionsIT = "Combina tutti gli ingredienti. Frulla fino a che \u00e8 liscio. Guarnire con scaglie di cioccolato se lo si desidera.",
                                    strInstructionsZHHANS = null,
                                    strInstructionsZHHANT = null,
                                    strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/rvwrvv1468877323.jpg",
                                    strIngredient1 = "Malibu rum",
                                    strIngredient2 = "Light rum",
                                    strIngredient3 = "151 proof rum",
                                    strIngredient4 = "Dark Creme de Cacao",
                                    strIngredient5 = "Cointreau",
                                    strIngredient6 = "Milk",
                                    strIngredient7 = "Coconut liqueur",
                                    strIngredient8 = "Vanilla ice - cream",
                                    strIngredient9 = null,
                                    strIngredient10 = null,
                                    strIngredient11 = null,
                                    strIngredient12 = null,
                                    strIngredient13 = null,
                                    strIngredient14 = null,
                                    strIngredient15 = null,
                                    strMeasure1 = "1/2 oz",
                                    strMeasure2 = "1/2 oz",
                                    strMeasure3 = "1/2 oz Bacardi",
                                    strMeasure4 = "1 oz",
                                    strMeasure5 = "1 oz",
                                    strMeasure6 = "3 oz",
                                    strMeasure7 = "1 oz",
                                    strMeasure8 = "1 cup",
                                    strMeasure9 = null,
                                    strMeasure10 = null,
                                    strMeasure11 = null,
                                    strMeasure12 = null,
                                    strMeasure13 = null,
                                    strMeasure14 = null,
                                    strMeasure15 = null,
                                    strImageSource = null,
                                    strImageAttribution = null,
                                    strCreativeCommonsConfirmed = "No",
                                    dateModified = "2016-07-18 22:28:43"
                                )
                            )
                        )
                    )
                }

                "Non_Alcoholic" -> {
                    continuation.onSuccess(
                        data = CocktailDTO(
                            listOf(
                                DrinkDTO(
                                    dateModified = "2016-07-18 22:07:32",
                                    idDrink = "12560",
                                    strAlcoholic = "Non alcoholic",
                                    strCategory = "Cocktail",
                                    strCreativeCommonsConfirmed = "No",
                                    strDrink = "Afterglow",
                                    strDrinkAlternate = null,
                                    strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/vuquyv1468876052.jpg",
                                    strGlass = "Highball Glass",
                                    strIBA = null,
                                    strImageAttribution = null,
                                    strImageSource = null,
                                    strIngredient1 = "Grenadine",
                                    strIngredient2 = "Orange juice",
                                    strIngredient3 = "Pineapple juice",
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
                                    strInstructions = "Mix. Serve over ice.",
                                    strInstructionsDE = "Mischen. Auf Eis servieren.",
                                    strInstructionsES = "Mezcla. Servir con hielo.",
                                    strInstructionsFR = null,
                                    strInstructionsIT = "Servire con ghiaccio.Mescolare.",
                                    strMeasure1 = "1 part",
                                    strMeasure2 = "4 part",
                                    strMeasure3 = "4 part",
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
                            )
                        )
                    )
                }

                "Optional_Alcohol" -> {
                    continuation.onSuccess(
                        data = CocktailDTO(
                            listOf(
                                DrinkDTO(
                                    dateModified = "2016-02-03 15:26:58",
                                    idDrink = "12864",
                                    strAlcoholic = "Optional alcohol",
                                    strCategory = "Punch / Party Drink",
                                    strCreativeCommonsConfirmed = "No",
                                    strDrink = "Apple Cider Punch",
                                    strDrinkAlternate = null,
                                    strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/xrqxuv1454513218.jpg",
                                    strGlass = "Collins Glass",
                                    strIBA = null,
                                    strImageAttribution = null,
                                    strImageSource = null,
                                    strIngredient1 = "Apple cider",
                                    strIngredient2 = "Brown sugar",
                                    strIngredient3 = "Lemonade",
                                    strIngredient4 = "Orange juice",
                                    strIngredient5 = "Cloves",
                                    strIngredient6 = "Allspice",
                                    strIngredient7 = "Nutmeg",
                                    strIngredient8 = "Cinnamon",
                                    strIngredient9 = null,
                                    strIngredient10 = null,
                                    strIngredient11 = null,
                                    strIngredient12 = null,
                                    strIngredient13 = null,
                                    strIngredient14 = null,
                                    strIngredient15 = null,
                                    strInstructions = "If you use the whole all spice and cloves, tie them in cheesecloth. Heat the mixture. Stir occasionally. If you want an alcoholic drink, rum would be nice.",
                                    strInstructionsDE = "Wenn du das ganze Gew\\u00fcrz und die Nelken verwendest, bindest du sie in ein Seihtuch. Die Mischung erhitzen. Gelegentlich umr\\u00fchren. Wenn du ein alkoholisches Getr\\u00e4nk willst, w\\u00e4re Rum sch\\u00f6n.",
                                    strInstructionsES = "Si va a usar todo el condimento y los clavos, \\u00e1telos en una gasa. Calentar la mezcla. Revuelva ocasionalmente. Si quieres una bebida alcoh\\u00f3lica, el ron ser\\u00eda agradable.",
                                    strInstructionsFR = null,
                                    strInstructionsIT = "Versa tutto in un pentolino tranne spezie e chiodi di garofano, legali in una garza e tienili in ammollo.\\r\\nRiscalda la miscela. Mescola di tanto in tanto. Se vuoi una bevanda alcolica puoi aggiungere un po\\u2019 di Rum.",
                                    strMeasure1 = "4 qt",
                                    strMeasure2 = "1 cup",
                                    strMeasure3 = "6 oz frozen",
                                    strMeasure4 = "6 oz frozen",
                                    strMeasure5 = "6 whole",
                                    strMeasure6 = "6 whole",
                                    strMeasure7 = "1 tsp ground",
                                    strMeasure8 = "3 sticks",
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
                            )
                        )
                    )
                }
            }
        }
    }

}