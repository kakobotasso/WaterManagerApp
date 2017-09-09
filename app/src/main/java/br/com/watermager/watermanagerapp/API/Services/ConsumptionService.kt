package br.com.watermager.watermanagerapp.API.Services

import br.com.watermager.watermanagerapp.API.CustomRetrofitResponse
import br.com.watermager.watermanagerapp.API.RetrofitConfiguration
import br.com.watermager.watermanagerapp.Models.Consumption
import retrofit2.Response

class ConsumptionService : CustomRetrofitResponse(){
    val service = RetrofitConfiguration.getService()

    fun getConsumptionList(type: String,
                           success: (response: Response<List<Consumption>>) -> Unit,
                           failure: (t: Throwable) -> Unit) {
        service.getConsumption(type).enqueue(success, failure)
    }
}