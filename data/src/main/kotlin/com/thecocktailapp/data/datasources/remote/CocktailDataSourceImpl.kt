package com.thecocktailapp.data.datasources.remote

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thecocktailapp.data.dto.CocktailDTO
import com.thecocktailapp.data.dto.ErrorDTO
import com.thecocktailapp.data.dto.ResultDTO
import com.thecocktailapp.data.utils.UrlConstants
import com.thecocktailapp.data.utils.onError
import com.thecocktailapp.data.utils.onSuccess
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class CocktailDataSourceImpl : CocktailDataSource {

    override suspend fun getDrinkById(id: Int): ResultDTO<CocktailDTO> =
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

    override suspend fun getDrinksByType(alcoholic: String): ResultDTO<CocktailDTO> =
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

    override suspend fun getRandomDrink(): ResultDTO<CocktailDTO> =
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

    private fun <T> CancellableContinuation<ResultDTO<T>>.doRequest(
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
            onError(error = ErrorDTO.Generic(response.code))
        }
    }

    private fun CancellableContinuation<ResultDTO<CocktailDTO>>.getCocktailDTO(response: Response) {
        val moshiBuilder = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonResult = try {
            val dto: CocktailDTO? = moshiBuilder.adapter(CocktailDTO::class.java)
                .fromJson(response.body?.string().orEmpty())
            if (dto != null) {
                ResultDTO.Response(dto)
            } else {
                ResultDTO.Error(ErrorDTO.DataNotFound)
            }
        } catch (ex: JsonDataException) {
            ResultDTO.Error(ErrorDTO.DeserializingJSON)
        } catch (ex: Exception) {
            ResultDTO.Error(ErrorDTO.LoadingData)
        }
        when (jsonResult) {
            is ResultDTO.Error<*> -> {
                onError(error = jsonResult.code as ErrorDTO)
            }

            is ResultDTO.Response -> {
                onSuccess(data = jsonResult.data)
            }
        }
    }

}