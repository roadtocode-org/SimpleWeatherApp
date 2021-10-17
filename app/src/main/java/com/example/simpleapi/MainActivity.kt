package com.example.simpleapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvTemp: TextView = findViewById(R.id.tvTemp)
        val etCityName: EditText = findViewById(R.id.etCityName)

        val tvCityName: TextView = findViewById(R.id.tvCityName)
        val tvDescription: TextView = findViewById(R.id.tvDescription)

        val ivSearch: ImageView = findViewById(R.id.ivSearch)
        ivSearch.setOnClickListener {

            val city = etCityName.text.toString()

            val call = ApiInterface.create().getWeatherInfo(city)

            call.enqueue(object: Callback<WeatherData> {

                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {

                    val k = response.body()?.main?.temp.toString().toFloat()
                    val c = (k - 273.5).toInt()
                    tvTemp.text =  c.toString() + "°C"

                    tvCityName.text = response.body()?.name.toString()
                    tvDescription.text = response.body()?.weather?.get(0)?.description

                    Log.d("RESPONSE"," Data: "+response.body().toString())
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    Log.d("RESPONSE"," Error: "+t.message)
                }

            })
        }
    }
}


