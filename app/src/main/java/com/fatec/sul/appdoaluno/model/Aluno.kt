package com.fatec.sul.appdoaluno.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "aluno")
data class Aluno (
    @SerializedName("MPW0041vPRO_PESSOALNOME")
    @ColumnInfo(name = "nome")
    var nome: String = "",

    @SerializedName("MPW0041vACD_ALUNOCURSOREGISTROACADEMICOCURSO")
    @ColumnInfo(name = "ra")
    @PrimaryKey
    var ra: Long  = 0,

    @SerializedName("vUNI_UNIDADENOME_MPAGE")
    @ColumnInfo(name = "unidade")
    var unidade: String = "",

    @SerializedName("MPW0041vACD_ALUNOCURSOCICLOATUAL")
    @ColumnInfo(name = "ciclo")
    var ciclo: Int = 0,

    @SerializedName("MPW0041vACD_ALUNOCURSOINDICEPP")
    @ColumnInfo(name = "pp")
    var pp: Float = 0.0f,

    @SerializedName("MPW0041vINSTITUCIONALETEC")
    @ColumnInfo(name = "emailFatec")
    var emailFatec: String?,

    @SerializedName("MPW0041vINSTITUCIONALFATEC")
    @ColumnInfo(name = "emailEtec")
    var emailEtec: String?,

    @SerializedName("MPW0041vFALTA")
    @ColumnInfo(name = "prazo")
    var prazo: Int?,

    @ColumnInfo(name = "usuarioID")
    var usuarioID: Long,
)