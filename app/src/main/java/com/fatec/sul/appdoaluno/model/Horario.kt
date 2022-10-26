package com.fatec.sul.appdoaluno.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horario")
class Horario (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,
    @ColumnInfo(name = "turno")
    var turno: Int = 0,
    @ColumnInfo(name = "sigla")
    var sigla: String = "",
    @ColumnInfo(name = "hora")
    var hora: String = "",
    @ColumnInfo(name = "diaDaSemana")
    var diaDaSemana: String = ""
)