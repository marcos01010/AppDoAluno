package com.fatec.sul.appdoaluno.model.api

import com.fatec.sul.appdoaluno.model.api.Atividade
import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime
import java.util.*

data class Chamada (@SerializedName("id") val id:Long,
                    @SerializedName("atividade") val atividade: Atividade,
                    @SerializedName("professor") val professor: Usuario,
                    @SerializedName("data") val data: String)