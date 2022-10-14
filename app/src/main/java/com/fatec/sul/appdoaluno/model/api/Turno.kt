package com.fatec.sul.appdoaluno.model.api

import com.google.gson.annotations.SerializedName

class Turno(@SerializedName("id") val id: Long,
            @SerializedName("descricao") val descricao: String)