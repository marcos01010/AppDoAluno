package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class Aceno (@SerializedName("sala") val sala: Sala,
                  @SerializedName("usuario") val usuario: Usuario,
                  @SerializedName("atividade") val atividade: Atividade,
                  @SerializedName("data") val data: String,
                  @SerializedName("descricao") val descricao: String)