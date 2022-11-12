package com.fatec.sul.appdoaluno.repository

import android.content.Context
import com.fatec.sul.appdoaluno.model.Professor
import com.fatec.sul.appdoaluno.services.local.DataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfessorRepository(private val context: Context) {

    private val materiaDao = DataBase.getDatabase(context).materiaDao()

    suspend fun buscarProfessores():List<Professor> {
        return withContext(Dispatchers.Default){
            val professor = materiaDao.buscarProfessores()
            professor.map { nome ->
                Professor(nome, materiaDao.buscarMateriaProfessor(nome))
            }
        }
    }
}