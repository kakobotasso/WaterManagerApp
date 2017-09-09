package br.com.watermager.watermanagerapp.API

import br.com.watermager.watermanagerapp.API.Interfaces.WaterManagerInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfiguration {
    companion object {
        val PRODUCTION = "https://watermanager.herokuapp.com"

        fun getService(): WaterManagerInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(PRODUCTION)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WaterManagerInterface::class.java)
        }
    }
}