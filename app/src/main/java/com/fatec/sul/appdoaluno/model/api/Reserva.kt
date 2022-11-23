package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class Reserva (@SerializedName("id") val id: Long = 0,
                    @SerializedName("inicio") val inicio :String,
                    @SerializedName("fim") val fim: String,
                    @SerializedName("numeroSala") val numeroSala: Int = 0,
                    @SerializedName("nomeSolicitante") val nomeSolicitante: String = "",
                    @SerializedName("usuarioID") var usuarioID: Long = 0,
                    @SerializedName("salaID") var salaID: Long = 0,
                    @SerializedName("descricao") var descricao: String = "")