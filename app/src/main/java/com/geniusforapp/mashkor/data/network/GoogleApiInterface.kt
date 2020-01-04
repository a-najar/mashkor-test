package com.geniusforapp.mashkor.data.network

import com.geniusforapp.mashkor.BuildConfig
import com.geniusforapp.mashkor.data.models.google.directions.LocationsDirections
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
interface GoogleMapApi {
    @GET("directions/json")
    fun getDirections(
        @Query("origin")
        origin: String? = null,
        @Query("destination")
        destination: String? = null,
        @Query("mode")
        mode: String? = null,
        @Query("key")
        apiKey: String = BuildConfig.GOOGLE_API_KEY
    ): Single<LocationsDirections>
}

@Suppress("FunctionName")
fun GoogleMapApi(): GoogleMapApi = Retrofit.Builder()
    .baseUrl(BuildConfig.GOOGLE_API_URL)
    .client(client)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(GoogleMapApi::class.java)