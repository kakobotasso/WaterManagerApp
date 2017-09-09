package br.com.watermager.watermanagerapp.API.Services

import br.com.watermager.watermanagerapp.API.CustomRetrofitResponse
import br.com.watermager.watermanagerapp.API.RetrofitConfiguration
import br.com.watermager.watermanagerapp.Models.Login
import br.com.watermager.watermanagerapp.Models.User
import retrofit2.Response

class LoginService : CustomRetrofitResponse() {
    val service = RetrofitConfiguration.getService()

    fun loginUser(login: Login,
                  success: (response: Response<User>) -> Unit,
                  failure: (t: Throwable) -> Unit) {
        service.signIn(login).enqueue(success, failure)
    }
}