package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class SalaApi(@SerializedName("id") val id:Long,
                   @SerializedName("numero") val numero:Int,
                   @SerializedName("predioId") val predioId:Int,
                   @SerializedName("descricaoPredio") val descricaoPredio:String,
                   @SerializedName("descricaoSala") val descricaoSala: String)