package com.fatec.sul.appdoaluno.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fatec.sul.appdoaluno.model.Aluno

@Dao
interface AlunoDao {
    @Insert
    fun salvar(alunoModel: Aluno)

    @Query("SELECT * FROM aluno")
    fun buscarTodos():List<Aluno>

    @Query("SELECT * FROM aluno limit 1")
    fun buscarAluno(): Aluno

    @Query("delete from aluno")
    fun deletarTodos()
}