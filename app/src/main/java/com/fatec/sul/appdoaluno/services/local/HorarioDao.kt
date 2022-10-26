package com.fatec.sul.appdoaluno.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fatec.sul.appdoaluno.model.Horario

@Dao
interface HorarioDao {
    @Insert
    fun save(horaios: List<Horario>)

    @Query("select * from horario")
    fun buscarTodos(): List<Horario>

    @Query("select hora from horario where sigla like :sigla")
    fun buscarHorario(sigla: String): String

    @Query("delete from horario")
    fun deletarTodos();
}