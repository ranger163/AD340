package me.inassar.ad340

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()
    private lateinit var dailyForecastAdapter: DailyForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        interactions()
    }

    private fun init() {
        forecastRepository.weeklyForecast.observe(this, ::updateUi)
        dailyForecastAdapter = DailyForecastAdapter(::forecastItemClick)
        forecastRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = dailyForecastAdapter
        }
    }

    private fun forecastItemClick(dailyForecast: DailyForecast) {
        Toast.makeText(this, dailyForecast.temp.toString(), Toast.LENGTH_LONG).show()
    }

    private fun interactions() {
        enterButton.setOnClickListener {
            if (zipcodeEditText.text.toString().length != 5) {
                Toast.makeText(this, "zipCode entry error", Toast.LENGTH_SHORT).show()
            } else {
                forecastRepository.loadForecast(zipcodeEditText.text.toString())
            }
        }
    }

    private fun updateUi(forecastItems: List<DailyForecast>) {
        dailyForecastAdapter.submitList(forecastItems)
    }
}