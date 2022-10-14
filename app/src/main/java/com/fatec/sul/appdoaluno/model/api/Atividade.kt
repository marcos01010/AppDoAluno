package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

data class Atividade(@SerializedName("id") val id: Long,
                     @SerializedName("materia") val materia: Materia,
                     @SerializedName("sala") val sala: Sala?)