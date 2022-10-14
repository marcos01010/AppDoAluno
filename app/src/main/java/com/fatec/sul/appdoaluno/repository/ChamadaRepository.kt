package com.fatec.sul.appdoaluno.repository

import android.content.Context
import com.fatec.sul.appdoaluno.model.api.Chamada
import com.fatec.sul.appdoaluno.services.local.DataBase
import com.fatec.sul.appdoaluno.services.remote.ApiService
import com.fatec.sul.appdoaluno.util.SingletonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.function.Consumer

class ChamadaRepository(context: Context) {
    private val materiaDao = DataBase.getDatabase(context).materiaDao()
    private val mRemote = RetrofitClient.createService(ApiService::class.java)

    suspend fun listarChamadas(mensagem:Consumer<String>): List<Chamada>{
        return withContext(Dispatchers.Default){
            val siglas = materiaDao.buscarTodasSiglas()
            SingletonApi.destino = SingletonApi.API
            val response = mRemote.listarChamadas(siglas).execute()
            SingletonApi.destino = SingletonApi.SIGA
            val chamadas = response.body()

            when(response.code()){
                200 -> {
                    //.accept("Busca realizada com sucesso")
                    chamadas ?: listOf()
                }
                204 -> {
                    //mensagem.accept("Nenhuma chamada aberta")
                    chamadas ?: listOf()
                }
                400 -> {
                    //mensagem.accept("Erro 400: Contate o desenvolvedor ou tente novamente")
                    listOf()
                }
                500 -> {
                   //mensagem.accept("Erro 500: Contate o desenvolvedor ou tente novamente mais tarde")
                    listOf()
                }
                else -> {
                    //mensagem.accept("${response.code()}: Algo inesperado aconteceu")
                    chamadas ?: listOf()
                }
            }
        }
    }

    suspend fun abrirChamada(chamada: Chamada){

    }

    fun responderChamada(chamdaID: Long): Boolean {
        TODO("Not yet implemented")
    }
}