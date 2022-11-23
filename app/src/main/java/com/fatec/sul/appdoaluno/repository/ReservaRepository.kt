package com.fatec.sul.appdoaluno.repository

import android.content.Context
import com.fatec.sul.appdoaluno.model.api.Reserva
import com.fatec.sul.appdoaluno.model.api.SalaApi
import com.fatec.sul.appdoaluno.services.local.DataBase
import com.fatec.sul.appdoaluno.services.remote.ApiService
import com.fatec.sul.appdoaluno.util.SingletonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReservaRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(ApiService::class.java)
    private val alunoDao = DataBase.getDatabase(context).alunoDao()

    suspend fun buscarReservas(): List<Reserva>{
        return withContext(Dispatchers.Default){
            SingletonApi.destino = SingletonApi.API
            val response = mRemote.buscarReservas().execute()
            SingletonApi.destino = SingletonApi.SIGA
            val reservas = response.body()

            when(response.code()){
                200 -> {
                    reservas ?: listOf()
                }
                204 -> {
                    reservas ?: listOf()
                }
                400 -> {
                    listOf()
                }
                500 -> {
                    listOf()
                }
                else -> {
                    reservas ?: listOf()
                }
            }
        }
    }

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

    suspend fun reservar(reserva: Reserva):Boolean {
        return withContext(Dispatchers.Default){
            try {
                val aluno = alunoDao.buscarAluno()
                SingletonApi.destino = SingletonApi.API
                val response = mRemote.reservar(reserva.also { it.usuarioID = aluno.usuarioID }).execute()
                SingletonApi.destino = SingletonApi.SIGA

                response.body() != null && response.body()!!
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}