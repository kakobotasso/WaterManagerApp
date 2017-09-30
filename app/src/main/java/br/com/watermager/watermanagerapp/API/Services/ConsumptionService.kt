package br.com.watermager.watermanagerapp.API.Services

import br.com.watermager.watermanagerapp.API.CustomRetrofitResponse
import br.com.watermager.watermanagerapp.API.RetrofitConfiguration
import br.com.watermager.watermanagerapp.Models.Consumption
import br.com.watermager.watermanagerapp.Models.ConsumptionResult
import retrofit2.Response

class ConsumptionService : CustomRetrofitResponse(){
    val service = RetrofitConfiguration.getService()

    fun getConsumptionList(serial: String,
                           type: String,
                           success: (response: Response<List<Consumption>>) -> Unit,
                           failure: (t: Throwable) -> Unit) {
        service.getConsumption(serial, type).enqueue(success, failure)
    }

    fun getEstimatedConsumption(serial: String,
                                type: String,
                                success: (response: Response<Consumption>) -> Unit,
                                failure: (t: Throwable) -> Unit) {
        service.getEstimatedConsumption(serial, type).enqueue(success, failure)
    }

    fun getEstimatedMonthlyConsumption(serial: String,
                                       type: String,
                                       success: (response: Response<ConsumptionResult>) -> Unit,
                                       failure: (t: Throwable) -> Unit) {
        service.getEstimatedAndMonthlyConsumption(serial, type).enqueue(success, failure)
    }
}