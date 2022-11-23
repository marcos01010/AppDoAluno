package com.fatec.sul.appdoaluno.services.remote

import com.fatec.sul.appdoaluno.model.api.*
import com.fatec.sul.appdoaluno.model.api.delivery.AcenoDelivery
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

    @GET("/sala")
    fun buscarLocais(): Call<List<SalaApi>>

    @POST("/aceno")
    fun criarAceno(@Body aceno: Aceno): Call<Boolean>

    @POST("/aceno/confirmar")
    fun confirmarAceno(@Query("id") id: Long): Call<Any>

    @GET("/aceno")
    fun buscarAcenos(@Query("id") id:Long): Call<List<AcenoDelivery>>

    @GET("/reserva")
    fun buscarReservas(): Call<List<Reserva>>

    @POST("/reserva/novo")
    fun reservar(@Body reserva: Reserva): Call<Boolean>
}