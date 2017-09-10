package br.com.watermager.watermanagerapp.API

import br.com.watermager.watermanagerapp.API.Interfaces.WaterManagerInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfiguration {
    companion object {
        val PRODUCTION = "https://watermanager.herokuapp.com"
        val httpClient = OkHttpClient.Builder()

        fun getService(): WaterManagerInterface {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)

            val retrofit = Retrofit.Builder()
                    .baseUrl(PRODUCTION)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()

            return retrofit.create(WaterManagerInterface::class.java)
        }
    }
}