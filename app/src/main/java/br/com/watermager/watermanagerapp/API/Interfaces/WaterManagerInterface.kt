package br.com.watermager.watermanagerapp.API.Interfaces

import br.com.watermager.watermanagerapp.Models.*
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

    @GET("/v1/consumption/monthly/{serial}/{consumption_type}")
    fun getConsumption(@Path("serial") serial: String,
                       @Path("consumption_type") type: String): Call<List<Consumption>>

    @GET("/v1/consumption/estimated/{serial}/{consumption_type}")
    fun getEstimatedConsumption(@Path("serial") serial: String,
                                @Path("consumption_type") type: String): Call<Consumption>

    @GET("/v1/consumption/monthly/estimated/{serial}/{consumption_type}")
    fun getEstimatedAndMonthlyConsumption(@Path("serial") serial: String,
                                          @Path("consumption_type") type: String): Call<ConsumptionResult>
}