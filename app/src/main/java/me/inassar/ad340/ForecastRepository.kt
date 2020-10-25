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
            DailyForecast(temperature, getTempDesc(temperature))
        }
        _weeklyForecast.value = forecastItems
    }

    private fun getTempDesc(temp: Float): String {
        return when (temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Anything below 0 doesn't make sense."
            in 0f.rangeTo(32f) -> "Way too cold."
            in 32f.rangeTo(55f) -> "Colder than I would prefer."
            in 55f.rangeTo(65f) -> "Getting better."
            in 65f.rangeTo(80f) -> "That's the sweet spot!"
            in 80f.rangeTo(90f) -> "Getting a little worm."
            in 90f.rangeTo(100f) -> "Dude, turn on the A/C."
            in 100f.rangeTo(Float.MAX_VALUE) -> "OK you are in hell now :D"
            else -> "Doesn't compute"
        }
    }
}