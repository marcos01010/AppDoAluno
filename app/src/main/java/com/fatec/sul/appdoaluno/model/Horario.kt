package com.fatec.sul.appdoaluno.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horario")
class Horario (
    @ColumnInfo(name = "turno")
    var turno: Int = 0,
    @ColumnInfo(name = "sigla")
    @PrimaryKey
    var sigla: String = "",
    @ColumnInfo(name = "hora")
    var hora: String = "",
    @ColumnInfo(name = "diaDaSemana")
    var diaDaSemana: String = ""
)