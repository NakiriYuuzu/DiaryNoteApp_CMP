package net.yuuzu.diarynoteapp.data.remote

import kotlinx.serialization.Serializable
import net.yuuzu.diarynoteapp.data.model.Main
import net.yuuzu.diarynoteapp.data.model.Weather

@Serializable
data class WeatherResponse(
    val cod: Int,
    val main: Main,
    val weather: List<Weather>
)