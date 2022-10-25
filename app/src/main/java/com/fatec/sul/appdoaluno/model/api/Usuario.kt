package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("id") val id: Long,
    @SerializedName("nome") val nome: String,
    @SerializedName("sobreNome") val sobreNome: String,
    @SerializedName("perfil") val perfil: Perfil,
    @SerializedName("hashChamada") var hashChamada:String,
    @SerializedName("ra") var ra:Long)