package net.yuuzu.diarynoteapp.data.model

import kotlinx.serialization.Serializable
import net.yuuzu.diarynoteapp.data.model.Main
import net.yuuzu.diarynoteapp.data.model.Weather

@Serializable
data class WeatherData(
    val main: Main,
    val weather: List<Weather>,
)