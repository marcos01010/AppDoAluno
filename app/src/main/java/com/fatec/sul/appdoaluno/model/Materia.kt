package com.fatec.sul.appdoaluno.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "materia")
data class Materia (
    @SerializedName("ACD_DisciplinaSigla")
    @ColumnInfo(name = "sigla")
    @PrimaryKey
    var sigla: String = "",
    @SerializedName("ACD_DisciplinaNome")
    @ColumnInfo(name = "descricao")
    var descricao: String = "",
    @SerializedName("ACD_AlunoHistoricoPeriodoOferecimentoId")
    @ColumnInfo(name = "ACD_AlunoHistoricoPeriodoOferecimentoId")
    var ACD_AlunoHistoricoPeriodoOferecimentoId: Int = 0,
    @SerializedName("ACD_AlunoHistoricoMediaFinal")
    @ColumnInfo(name = "media")
    var media: Float = 0f,
    @SerializedName("ACD_AlunoHistoricoFrequencia")
    @ColumnInfo(name = "frequencia")
    var frquencia: Float = 0f,
    @SerializedName("ACD_AlunoHistoricoItemQtdFaltas")
    @ColumnInfo(name = "faltas")
    var faltas: Int = 0,
    @SerializedName("ACD_AlunoHistoricoAprovada")
    @ColumnInfo(name = "aprovado")
    var aprovado: Boolean = false,
    @SerializedName("CD_CursoCompPeriodoMedia")
    @ColumnInfo(name = "CD_CursoCompPeriodoMedia")
    var CD_CursoCompPeriodoMedia: Float = 0f,
    @SerializedName("ACD_CursoCompPeriodoPercFaltas")
    @ColumnInfo(name = "ACD_CursoCompPeriodoPercFaltas")
    var ACD_CursoCompPeriodoPercFaltas: Int = 0,
    @ColumnInfo(name = "professor")
    var professor: String = ""
)