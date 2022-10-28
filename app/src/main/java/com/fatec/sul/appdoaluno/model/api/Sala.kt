package com.fatec.sul.appdoaluno.model.api

import com.fatec.sul.appdoaluno.model.api.Predio
import com.google.gson.annotations.SerializedName

data class Sala(@SerializedName("id") val id: Long,
                @SerializedName("predio") val predio: Predio = Predio(),
                @SerializedName("capacidade") val capacidade:Int = 0,
                @SerializedName("numero") val numero:Int = 0)