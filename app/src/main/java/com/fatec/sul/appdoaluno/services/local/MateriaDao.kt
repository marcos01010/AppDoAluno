package com.fatec.sul.appdoaluno.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fatec.sul.appdoaluno.model.Materia

@Dao
interface MateriaDao {
    @Insert
    fun save(materias: List<Materia>)

    @Query("select * from materia where aprovado = 0")
    fun buscarTodas(): List<Materia>

    @Query("select sigla from materia where aprovado = 0")
    fun buscarTodasSiglas(): List<String>

    @Query("delete from materia")
    fun deletarTodas();

    @Query("select professor from materia where professor not like ''")
    fun buscarProfessores(): List<String>

    @Query("select descricao from materia where professor like :nome")
    fun buscarMateriaProfessor(nome: String): List<String>
}