package com.example.clima.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.clima.R
import com.example.clima.databinding.ActivityWeatherDetailsBinding
import com.example.clima.repositories.WeatherRepository
import com.example.clima.responses.WeatherSimpleResponse
import com.example.clima.utils.Constants.INTENT_WEATHER_RESULT
import com.example.clima.utils.Status
import com.example.clima.viewmodels.WeatherDetailsActivityViewModel
import com.example.clima.viewmodels.WeatherDetailsActivityViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherDetailsActivity : AppCompatActivity() {

    private lateinit var cityId: String
    private lateinit var mViewModel: WeatherDetailsActivityViewModel
    private lateinit var binding: ActivityWeatherDetailsBinding
    @Inject
    lateinit var weatherRepository: WeatherRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_details)

        cityId = intent.getStringExtra(INTENT_WEATHER_RESULT)!!

        setupViewModel()
        doInitialization()
        setupObservers()
    }

    private fun setupViewModel() {
        mViewModel = ViewModelProviders.of(
            this,
            WeatherDetailsActivityViewModelFactory(weatherRepository)
        ).get(WeatherDetailsActivityViewModel::class.java)
    }

    private fun doInitialization() {
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupObservers() {
        mViewModel.getWeatherByCityId(cityId).observe(this, {
            it?.let { r ->
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.isLoading = false
                        it.data?.let { weatherResponse ->
                            retrieveWeatherDetails(weatherResponse)
                        }
                        binding.layoutWeatherData.visibility = View.VISIBLE
                    }

                    Status.ERROR -> {
                        binding.isLoading = false
                        binding.layoutWeatherData.visibility = View.GONE
                        Toast.makeText(this, r.message, Toast.LENGTH_LONG).show()
                    }

                    Status.LOADING -> {
                        binding.layoutWeatherData.visibility = View.GONE
                        binding.isLoading = true
                    }
                }
            }
        })
    }

    private fun retrieveWeatherDetails(weatherResponse: WeatherSimpleResponse) {
        val w = weatherResponse.list[0]
        binding.cityAndCountry = w.cityAndCountry
        binding.dateAndTime = w.dt
        binding.feelsLike = w.main.feelsLike
        binding.maximumTemperature = w.main.tempMax
        binding.minimumTemperature = w.main.tempMin
        binding.pressure = w.main.pressure
        binding.sunrise = w.sys.sunrise
        binding.sunset = w.sys.sunset
        binding.humidity = w.main.humidity
        binding.temperature = w.main.temp
        binding.wind = w.wind.speed
        binding.weatherDescription = w.weather[0].description
        binding.weatherIcon = w.weather[0].iconUrlType2
    }
}