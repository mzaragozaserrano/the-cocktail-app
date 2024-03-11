package com.thecocktailapp.data.datasources.services

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thecocktailapp.core.data.utils.ResultData
import com.thecocktailapp.core.data.utils.onError
import com.thecocktailapp.core.data.utils.onSuccess
import com.thecocktailapp.data.dto.CocktailDTO
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.data.utils.UrlConstants
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class CocktailDataSourceImpl @Inject constructor() : CocktailDataSource {

    override suspend fun getDrinkById(id: Int): ResultData<CocktailDTO> =
        suspendCancellableCoroutine { continuation ->
            with(continuation) {
                doRequest(
                    url = UrlConstants.GetDrinkById(id = id).url,
                    onSuccess = { response ->
                        getCocktailDTO(response)
                    }
                )
            }
        }

    override suspend fun getDrinksByType(alcoholic: String): ResultData<CocktailDTO> =
        suspendCancellableCoroutine { continuation ->
            with(continuation) {
                doRequest(
                    url = UrlConstants.GetDrinkByType(alcoholic = alcoholic).url,
                    onSuccess = { response ->
                        getCocktailDTO(response)
                    }
                )
            }
        }

    override suspend fun getRandomDrink(): ResultData<CocktailDTO> =
        suspendCancellableCoroutine { continuation ->
            with(continuation) {
                doRequest(
                    url = UrlConstants.GetRandomDrink.url,
                    onSuccess = { response ->
                        getCocktailDTO(response)
                    }
                )
            }
        }

    private fun <T> CancellableContinuation<ResultData<T>>.doRequest(
        url: String,
        onSuccess: (Response) -> Unit,
    ) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            onSuccess(response)
        } else {
            onError(this, ErrorDTO.Generic(response.code))
        }
    }

    private fun CancellableContinuation<ResultData<CocktailDTO>>.getCocktailDTO(response: Response) {
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
                onError(this, jsonResult.code as ErrorDTO)
            }

            is ResultData.Response -> {
                onSuccess(this, jsonResult.data)
            }
        }
    }

}