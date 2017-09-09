package br.com.watermager.watermanagerapp.API.Services

import br.com.watermager.watermanagerapp.API.CustomRetrofitResponse
import br.com.watermager.watermanagerapp.API.RetrofitConfiguration
import br.com.watermager.watermanagerapp.Models.Version
import retrofit2.Response

class VersionService : CustomRetrofitResponse() {
    val service = RetrofitConfiguration.getService()

    fun checkVersion(success: (response: Response<Version>) -> Unit,
                     failure: (t: Throwable) -> Unit) {
        service.checkVersion().enqueue(success, failure)
    }
}