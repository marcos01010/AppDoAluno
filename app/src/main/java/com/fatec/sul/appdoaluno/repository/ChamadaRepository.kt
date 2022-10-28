package com.fatec.sul.appdoaluno.repository

import android.content.Context
import com.fatec.sul.appdoaluno.model.api.Chamada
import com.fatec.sul.appdoaluno.model.api.Usuario
import com.fatec.sul.appdoaluno.services.local.DataBase
import com.fatec.sul.appdoaluno.services.remote.ApiService
import com.fatec.sul.appdoaluno.util.SingletonApi
import com.fatec.sul.appdoaluno.util.SingletonProfessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.function.Consumer

class ChamadaRepository(context: Context) {
    private val materiaDao = DataBase.getDatabase(context).materiaDao()
    private val alunoDao = DataBase.getDatabase(context).alunoDao()
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

    suspend fun abrirChamada(chamada: Chamada) :Boolean{
        return withContext(Dispatchers.Default){
            try {
                SingletonApi.destino = SingletonApi.API
                val response = mRemote.abrirChamada(chamada).execute()
                SingletonApi.destino = SingletonApi.SIGA
                when (response.code()) {
                    200 -> {
                        true
                    }
                    201 -> {
                        true
                    }
                    400 -> {
                        false
                    }
                    500 -> {
                        false
                    }
                    else -> {
                        false
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    suspend fun responderChamada(chamdaID: Long): Boolean {
        return withContext(Dispatchers.Default){
            SingletonApi.destino = SingletonApi.API
            val aluno = alunoDao.buscarAluno()
            val response = mRemote.responderChamada(chamdaID,aluno.usuarioID).execute()
            SingletonApi.destino = SingletonApi.SIGA

            when(response.code()){
                200 -> {
                    //.accept("Busca realizada com sucesso")
                    true
                }
                204 -> {
                    //mensagem.accept("Nenhuma chamada aberta")
                    true
                }
                400 -> {
                    //mensagem.accept("Erro 400: Contate o desenvolvedor ou tente novamente")
                    false
                }
                500 -> {
                    //mensagem.accept("Erro 500: Contate o desenvolvedor ou tente novamente mais tarde")
                   false
                }
                else -> {
                    //mensagem.accept("${response.code()}: Algo inesperado aconteceu")
                    false
                }
            }
        }
    }

    suspend fun buscarChamadas(hashChamada: String): List<Chamada> {
        return withContext(Dispatchers.Default){
            SingletonApi.destino = SingletonApi.API
            val response = mRemote.buscarChamadas(hashChamada).execute()
            SingletonApi.destino = SingletonApi.SIGA
            val chamadas = response.body() ?: listOf()

            when(response.code()){
                200 -> {
                    //.accept("Busca realizada com sucesso")
                    chamadas
                }
                204 -> {
                    //mensagem.accept("Nenhuma chamada aberta")
                    listOf()
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
                    listOf()
                }
            }
        }
    }

    suspend fun buscarPresentes(chamadaID: Long) : List<Usuario> {
        return withContext(Dispatchers.Default){
            SingletonApi.destino = SingletonApi.API
            val response = mRemote.buscarPresentes(chamadaID).execute()
            SingletonApi.destino = SingletonApi.SIGA
            val presentes = response.body() ?: listOf()

            when(response.code()){
                200 -> {
                    //.accept("Busca realizada com sucesso")
                    presentes
                }
                204 -> {
                    //mensagem.accept("Nenhuma chamada aberta")
                    listOf()
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
                    listOf()
                }
            }
        }
    }
}