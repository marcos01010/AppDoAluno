package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("id") val id: Long = 0L,
    @SerializedName("nome") val nome: String = "",
    @SerializedName("sobreNome") val sobreNome: String = "",
    @SerializedName("perfil") var perfil: Perfil = Perfil(1L, "Aluno"),
    @SerializedName("hashChamada") var hashChamada:String = "",
    @SerializedName("ra") var ra: Long =  0L)