package com.fatec.sul.appdoaluno.model

data class Login (
    var vSIS_USUARIOID: String = "",
    var vSIS_USUARIOSENHA: String = "",
    var gxState: String = "",
    var cookie : String = "",
    val BTCONFIRMA: String = "Confirmar"
)