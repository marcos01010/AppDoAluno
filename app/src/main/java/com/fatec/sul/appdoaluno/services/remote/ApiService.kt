package com.fatec.sul.appdoaluno.services.remote

import com.fatec.sul.appdoaluno.model.api.Chamada
import com.fatec.sul.appdoaluno.model.api.Materia
import com.fatec.sul.appdoaluno.model.api.Usuario
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/chamada")
    fun abrirChamada(@Body chamada: Chamada): Call<Boolean>

    @POST("/chamada/resposta")
    fun responderChamada(@Query("c") chamadaID: Long, @Query("u") usuarioID: Long): Call<Chamada>

    @POST("/chamada/ativas")
    fun listarChamadas(@Body siglasMateria: List<String>):Call<List<Chamada>>

    @POST("/chamada/presente")
    fun buscarPresentes(@Query("chamadaID") chamadaID: Long): Call<List<Usuario>>

    @POST("/chamada/ativas/professor")
    fun buscarChamadas(@Query("hashChamada") hashChamada: String):Call<List<Chamada>>

    @POST("/materia/atualizar")
    fun salvarMateriaAPI(@Body materias: List<Materia>): Call<Boolean>

    @GET("/materia")
    fun buscarMateriasProfessor(): Call<List<Materia>>

    @PATCH("/materia")
    fun assumirMateria(@Body materia: Materia): Call<Boolean>

    @POST("/materia/assumida")
    fun buscarMateriasProfessor(@Body hashChamada: String): Call<List<Materia>>

    @POST("/usuario/novo")
    fun salvarAlunoApi(@Body usuario: Usuario): Call<Usuario>
}