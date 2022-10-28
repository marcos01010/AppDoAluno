package com.fatec.sul.appdoaluno.model.api

import com.fatec.sul.appdoaluno.model.api.Atividade
import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime
import java.util.*

data class Chamada (@SerializedName("id") val id:Long,
                    @SerializedName("professor") val professor: Usuario,
                    @SerializedName("sigla") val sigla: String,
                    @SerializedName("materia") val descricao: String,
                    @SerializedName("data") val data: String,
                    @SerializedName("nomeProfeddor") val nomeProfessor: String,
                    @SerializedName("atividade") val atividade: Atividade)