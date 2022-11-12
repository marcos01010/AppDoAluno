package com.fatec.sul.appdoaluno.model.api.delivery

import com.fatec.sul.appdoaluno.model.api.SalaApi
import com.google.gson.annotations.SerializedName

data class AcenoDelivery (@SerializedName("local") val sala: SalaApi,
                          @SerializedName("nomeUsuario") val nomeUsuario: String,
                          @SerializedName("descricao") val descricao: String,
                          @SerializedName("nomeMateria") val nomeMateria: String,
                          @SerializedName("confirmacoes") val confirmacoes: Long,
                          @SerializedName("id") val id: Long)