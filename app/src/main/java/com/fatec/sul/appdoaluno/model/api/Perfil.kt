package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class Perfil(@SerializedName("id") val id:Long,
                  @SerializedName("descricao") val descricao:String = "")