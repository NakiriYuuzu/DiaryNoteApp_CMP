package net.yuuzu.diarynoteapp.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService: KtorApi() {
    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse =
        client.get {
            apiUrl("/data/2.5/weather")
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("appid", ApiConstant.API_KEY)
        }.body()
}