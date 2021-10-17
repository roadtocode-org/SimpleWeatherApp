package com.example.simpleapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    fun getWeatherInfo(
        @Query("q") q: String,
        @Query("appid") appid:String="071b8cb61b0450cbdaec0ce82cb33d78"): Call<WeatherData>

    companion object{

        var BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(): ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return  retrofit.create(ApiInterface::class.java)
        }
    }
}