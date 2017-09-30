package br.com.watermager.watermanagerapp.Models

import com.google.gson.annotations.SerializedName

class ConsumptionResult {
    lateinit var estimated: List<Consumption>

    @SerializedName("consumption_list")
    lateinit var consumptionList: List<Consumption>
}