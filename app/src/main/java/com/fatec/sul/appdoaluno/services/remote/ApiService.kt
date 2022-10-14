package com.fatec.sul.appdoaluno.services.remote

import com.fatec.sul.appdoaluno.model.api.Chamada
import com.fatec.sul.appdoaluno.model.api.Materia
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/chamada/ativas")
    fun listarChamadas(@Body siglasMateria: List<String>):Call<List<Chamada>>

    @GET("/materia")
    fun buscarMateriasProfessor(): Call<List<Materia>>

    @POST("/materia/assumida")
    fun buscarMateriasProfessor(@Body hashChamada: String): Call<List<Materia>>

    @PATCH("/materia")
    fun assumirMateria(@Body materia: Materia): Call<Boolean>

    @POST("/chamada")
    fun abrirChamada(@Body chamada: Chamada): Call<Boolean>

//    @POST("/resposta")
//    fun res(@Body chamada: Chamada): Call<Boolean>
}