package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class Materia ( @SerializedName("sigla") val sigla: String,
                     @SerializedName("descricao") val descricao: String,
                     @SerializedName("professor") var professor: Usuario?,
                     @SerializedName("turno") var turno: Turno,
                     @SerializedName("alunos") var alunos: List<Usuario>)