package com.fatec.sul.appdoaluno.repository

import android.content.Context
import com.fatec.sul.appdoaluno.model.api.*
import com.fatec.sul.appdoaluno.model.api.delivery.AcenoDelivery
import com.fatec.sul.appdoaluno.services.local.DataBase
import com.fatec.sul.appdoaluno.services.remote.ApiService
import com.fatec.sul.appdoaluno.util.SingletonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AcenoRepository (context: Context){
    private val mRemote = RetrofitClient.createService(ApiService::class.java)
    private val alunoDao = DataBase.getDatabase(context).alunoDao()

    suspend fun buscarLocais(): List<SalaApi>{
        return withContext(Dispatchers.Default){
            try {
                SingletonApi.destino = SingletonApi.API
                val response = mRemote.buscarLocais().execute()
                SingletonApi.destino = SingletonApi.SIGA
                val locais = response.body()

                when(response.code()){
                    200 -> {
                        //.accept("Busca realizada com sucesso")
                        locais ?: listOf()
                    }
                    204 -> {
                        //mensagem.accept("Nenhuma chamada aberta")
                        locais ?: listOf()
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
                        locais ?: listOf()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                listOf()
            }
        }
    }

    suspend fun buscarAcenos(): List<AcenoDelivery>{
        return withContext(Dispatchers.Default){
            try {
                SingletonApi.destino = SingletonApi.API
                val response = mRemote.buscarAcenos(alunoDao.buscarAluno().usuarioID).execute()
                SingletonApi.destino = SingletonApi.SIGA
                val acenos = response.body()

                when(response.code()){
                    200 -> {
                        //.accept("Busca realizada com sucesso")
                        acenos ?: listOf()
                    }
                    204 -> {
                        //mensagem.accept("Nenhuma chamada aberta")
                        acenos ?: listOf()
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
                        acenos ?: listOf()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                listOf()
            }
        }
    }

    suspend fun criarAceno(salaId: Int, descricao: String, materia: com.fatec.sul.appdoaluno.model.Materia) : Boolean{
        return withContext(Dispatchers.Default) {
            try {
                val aluno = alunoDao.buscarAluno()
                val usuario = Usuario(aluno.usuarioID)
                val sala = Sala(id = salaId.toLong())
                val turno = Turno(materia.turno)
                val atividade = Atividade(null, sala = sala, materia = Materia(sigla = materia.sigla, turno = turno))
                val aceno = Aceno(sala, usuario, atividade, "", descricao)

                SingletonApi.destino = SingletonApi.API
                val response = mRemote.criarAceno(aceno).execute()
                SingletonApi.destino = SingletonApi.SIGA

                when (response.code()) {
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
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}