package com.fatec.sul.appdoaluno.repository

import android.content.Context
import com.fatec.sul.appdoaluno.model.Horario
import com.fatec.sul.appdoaluno.model.Materia
import com.fatec.sul.appdoaluno.services.local.DataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MateriaRepository(context: Context) {
    private val materiaDao = DataBase.getDatabase(context).materiaDao()
    private val horarioDao = DataBase.getDatabase(context).horarioDao()

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
}