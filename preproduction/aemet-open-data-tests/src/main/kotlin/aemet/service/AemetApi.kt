package org.example.aemet.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.example.aemet.models.responses.AemetOpenDataResponse
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

object AemetApi {
    private const val BASE_URL: String = "https://opendata.aemet.es/opendata/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
    private val errorConverter: Converter<ResponseBody, AemetOpenDataResponse> =
        retrofit.responseBodyConverter(
            AemetOpenDataResponse::class.java,
            emptyArray<Annotation>()
        )

    val endpoints: IAemetForecastByCityApiService by lazy {
        retrofit.create<IAemetForecastByCityApiService>()
    }

    fun getErrorResponseBody(e: HttpException): AemetOpenDataResponse {
        val errorBody = e.response()?.errorBody()!!

        return errorConverter.convert(errorBody)!!
    }
}