package com.fatec.sul.appdoaluno.model.api

import com.fatec.sul.appdoaluno.model.api.Predio
import com.google.gson.annotations.SerializedName

data class Sala(@SerializedName("id") val id: Long,
                @SerializedName("predio") val predio: Predio,
                @SerializedName("capacidade") val capacidade:Int,
                @SerializedName("numero") val numero:Int)