package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class Predio(@SerializedName("id") val id:Long = 0L,
                  @SerializedName("descricao") val descricao:String = "")