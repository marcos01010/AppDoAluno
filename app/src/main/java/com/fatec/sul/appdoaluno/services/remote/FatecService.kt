package com.fatec.sul.appdoaluno.services.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface FatecService{
    @GET("ALUNO/login.aspx")
    fun home(): Call<ResponseBody>

    @FormUrlEncoded
    @POST("ALUNO/login.aspx")
    fun logar(@Field("vSIS_USUARIOID") usuario: String,
              @Field("vSIS_USUARIOSENHA") senha: String,
              @Field("BTCONFIRMA") btconfirma: String,
              @Field("GXState") gxState: String,
              @Header("cookie")  cookie: String): Call<ResponseBody>

    @GET("ALUNO/historico.aspx")
    fun materias(@Header("cookie")  cookie: String): Call<ResponseBody>

    @GET("ALUNO/horario.aspx")
    fun horario(@Header("cookie")  cookie: String): Call<ResponseBody>
}