package br.com.watermager.watermanagerapp.API.Interfaces

import br.com.watermager.watermanagerapp.Models.Consumption
import br.com.watermager.watermanagerapp.Models.Login
import br.com.watermager.watermanagerapp.Models.User
import br.com.watermager.watermanagerapp.Models.Version
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WaterManagerInterface {
    @GET("/version")
    fun checkVersion(): Call<Version>

    @POST("/v1/signin")
    fun signIn(@Body login: Login): Call<User>

    @POST("/v1/user")
    fun signUp(@Body user: User): Call<User>

    @GET("/v1/user/{id}")
    fun getUserInfo(@Path("id") id: String): Call<User>

    @GET("/v1/consumption/{consumption_type}")
    fun getConsumption(@Path("consumption_type") type: String): Call<List<Consumption>>
}