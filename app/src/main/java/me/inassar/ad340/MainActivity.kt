package me.inassar.ad340

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enterButton.setOnClickListener {
            val zipcode: String = zipcodeEditText.text.toString()

            if (zipcode.length != 5) {
                Toast.makeText(this, "zipCode entry error", Toast.LENGTH_SHORT).show()
            } else {
                forecastRepository.loadForecast(zipcode)
            }
        }

        forecastRepository.weeklyForecast.observe(this, ::updateUi)
    }

    private fun updateUi(forecastItems: List<DailyForecast>) {
        Toast.makeText(this, forecastItems.toString(), Toast.LENGTH_LONG).show()
    }
}