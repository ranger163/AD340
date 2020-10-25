package me.inassar.ad340

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.math.RoundingMode
import kotlin.random.Random

class ForecastRepository {

    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>>
        get() = _weeklyForecast

    fun loadForecast(zipCode: String) {
        val randomValues = List(7) {
            Random.nextFloat().rem(100).times(100).toBigDecimal()
                .setScale(2, RoundingMode.UP)
                .toFloat()
        }
        val forecastItems = randomValues.map { temperature ->
            DailyForecast(temperature, "Partly Cloudy")
        }
        _weeklyForecast.value = forecastItems
    }
}