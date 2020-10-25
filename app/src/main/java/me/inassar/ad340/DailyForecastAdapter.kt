package me.inassar.ad340

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_daily_forecast.view.*

class DailyForecastAdapter(
    private val itemClickListener: (DailyForecast) -> Unit
) :
    ListAdapter<DailyForecast, DailyForecastViewHolder>(DIFF_CONFIG) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        return DailyForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bindUi(getItem(position), itemClickListener)
    }

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}

class DailyForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindUi(dailyForecast: DailyForecast, itemClickListener: (DailyForecast) -> Unit) {
        itemView.apply {
            forecastTempTv.text = dailyForecast.temp.toString()
            forecastDescTv.text = dailyForecast.description
            setOnClickListener { itemClickListener(dailyForecast) }
        }
    }
}