package br.com.watermager.watermanagerapp.API

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class CustomRetrofitResponse {
    fun <T> Call<T>.enqueue(success: (response: Response<T>) -> Unit,
                            failure: (t: Throwable) -> Unit) {
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                success(response!!)
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                failure(t!!)
            }
        })
    }
}