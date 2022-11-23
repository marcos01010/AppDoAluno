package com.fatec.sul.appdoaluno.util

class SingletonApi {
    companion object {
        const val URL_SIGA = "https://siga.cps.sp.gov.br/"
        const val URL_API = "http://192.168.15.78:8080/"
        const val SIGA = 0
        const val API = 1
        var destino = SIGA
    }
}