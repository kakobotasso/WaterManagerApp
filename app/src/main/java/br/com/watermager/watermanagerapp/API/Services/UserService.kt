package br.com.watermager.watermanagerapp.API.Services

import br.com.watermager.watermanagerapp.API.CustomRetrofitResponse
import br.com.watermager.watermanagerapp.API.RetrofitConfiguration
import br.com.watermager.watermanagerapp.Models.User
import retrofit2.Response

class UserService : CustomRetrofitResponse() {
    val service = RetrofitConfiguration.getService()

    fun signUpUser(user: User,
                   success: (response: Response<User>) -> Unit,
                   failure: (t: Throwable) -> Unit) {
        service.signUp(user).enqueue(success, failure)
    }

    fun getUserInfo(userId: String,
                    success: (response: Response<User>) -> Unit,
                    failure: (t: Throwable) -> Unit) {
        service.getUserInfo(userId).enqueue(success, failure)
    }

}