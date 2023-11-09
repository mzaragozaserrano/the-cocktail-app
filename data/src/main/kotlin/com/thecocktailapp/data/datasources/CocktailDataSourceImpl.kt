package com.thecocktailapp.data.datasources

import com.mzaragozaserrano.data.utils.ResultData
import com.mzaragozaserrano.data.utils.onError
import com.mzaragozaserrano.data.utils.onSuccess
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thecocktailapp.data.dto.CocktailDTO
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.data.utils.UrlConstants.URL_RANDOM_COCKTAIL
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class CocktailDataSourceImpl @Inject constructor() : CocktailDataSource {

    override suspend fun getRandomDrink(): ResultData<CocktailDTO> =
        suspendCancellableCoroutine { continuation ->
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(URL_RANDOM_COCKTAIL)
                .build()
            try {
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val moshiBuilder = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                    val jsonResult = try {
                        val dto: CocktailDTO? = moshiBuilder.adapter(CocktailDTO::class.java)
                            .fromJson(response.body?.string() ?: "")
                        if (dto != null) {
                            ResultData.Response(dto)
                        } else {
                            ResultData.Error(ErrorDTO.DataNotFound)
                        }
                    } catch (ex: JsonDataException) {
                        ResultData.Error(ErrorDTO.DeserializingJSON)
                    } catch (ex: Exception) {
                        ResultData.Error(ErrorDTO.LoadingData)
                    }
                    when (jsonResult) {
                        is ResultData.Error<*> -> {
                            onError(continuation, jsonResult.code as ErrorDTO)
                        }

                        is ResultData.Response -> {
                            onSuccess(continuation, jsonResult.data)
                        }
                    }
                } else {
                    onError(continuation, ErrorDTO.Basic(response.code))
                }
            } catch (e: Exception) {
                onError(continuation, ErrorDTO.LoadingURL)
            }
        }

}