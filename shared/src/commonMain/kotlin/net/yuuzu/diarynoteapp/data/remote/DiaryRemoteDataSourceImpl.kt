package net.yuuzu.diarynoteapp.data.remote

import net.yuuzu.diarynoteapp.data.DiaryDataSource

class DiaryRemoteDataSourceImpl(
    private val apiService: ApiService
): DiaryDataSource.Remote {
    override suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        return apiService.getWeather(lat, lon)
    }
}