package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class Materia (@SerializedName("sigla") val sigla: String = "",
                    @SerializedName("descricao") val descricao: String = "",
                    @SerializedName("professor") var professor: Usuario? = Usuario(),
                    @SerializedName("turno") var turno: Turno =  Turno(),
                    @SerializedName("turnoID") var turnoID: Int = 0,
                    @SerializedName("alunos") var alunos: List<Usuario> = listOf()
)