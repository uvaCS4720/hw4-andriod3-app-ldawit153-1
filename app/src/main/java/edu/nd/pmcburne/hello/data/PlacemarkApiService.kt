package edu.nd.pmcburne.hello

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PlacemarkApiService {

    @GET("~wxt4gm/placemarks.json")
    suspend fun getPlacemarkLocations(): List<ApiLocation>

    companion object {
        fun create(): PlacemarkApiService {
            return Retrofit.Builder()
                .baseUrl("https://www.cs.virginia.edu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlacemarkApiService::class.java)
        }
    }
}