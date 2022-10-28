package com.fatec.sul.appdoaluno.repository

import android.content.Context
import com.fatec.sul.appdoaluno.model.Horario
import com.fatec.sul.appdoaluno.model.Materia
import com.fatec.sul.appdoaluno.model.api.Chamada
import com.fatec.sul.appdoaluno.model.api.Turno
import com.fatec.sul.appdoaluno.services.local.DataBase
import com.fatec.sul.appdoaluno.services.remote.ApiService
import com.fatec.sul.appdoaluno.util.DataHora
import com.fatec.sul.appdoaluno.util.SingletonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MateriaRepository(context: Context) {
    private val materiaDao = DataBase.getDatabase(context).materiaDao()
    private val horarioDao = DataBase.getDatabase(context).horarioDao()
    private val mRemote = RetrofitClient.createService(ApiService::class.java)

    suspend fun buscarMaterias(): List<Materia> {
        return withContext(Dispatchers.Default){
            materiaDao.buscarTodas()
        }
    }

    suspend fun buscarHorarios(): List<Horario> {
        return withContext(Dispatchers.Default){
            horarioDao.buscarTodos()
        }
    }

    suspend fun buscarMateriasProfessor(): List<com.fatec.sul.appdoaluno.model.api.Materia> {
        return withContext(Dispatchers.Default) {
            try {
                SingletonApi.destino = SingletonApi.API
                val response = mRemote.buscarMateriasProfessor().execute()
                SingletonApi.destino = SingletonApi.SIGA
                val materias = response.body()

                when (response.code()) {
                    200 -> {
                        materias ?: listOf()
                    }
                    204 -> {
                        materias ?: listOf()
                    }
                    400 -> {
                        listOf()
                    }
                    500 -> {
                        listOf()
                    }
                    else -> {
                        materias ?: listOf()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                listOf()
            }
        }
    }

    suspend fun buscarMateriasProfessor(hashChamada: String): List<com.fatec.sul.appdoaluno.model.api.Materia> {
        return withContext(Dispatchers.Default) {
            try {
                SingletonApi.destino = SingletonApi.API
                val response = mRemote.buscarMateriasProfessor(hashChamada).execute()
                SingletonApi.destino = SingletonApi.SIGA
                val materias = response.body()

                when (response.code()) {
                    200 -> {
                        materias ?: listOf()
                    }
                    204 -> {
                        materias ?: listOf()
                    }
                    400 -> {
                        listOf()
                    }
                    500 -> {
                        listOf()
                    }
                    else -> {
                        materias ?: listOf()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                listOf()
            }
        }
    }

    suspend fun assumirMateria(materia: com.fatec.sul.appdoaluno.model.api.Materia): Boolean {
        return withContext(Dispatchers.Default) {
            try {
                SingletonApi.destino = SingletonApi.API
                val response = mRemote.assumirMateria(materia).execute()
                SingletonApi.destino = SingletonApi.SIGA

                when (response.code()) {
                    200 -> {
                        true
                    }
                    204 -> {
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
}