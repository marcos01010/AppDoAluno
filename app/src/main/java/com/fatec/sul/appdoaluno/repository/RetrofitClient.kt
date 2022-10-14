package com.fatec.sul.appdoaluno.repository

import com.fatec.sul.appdoaluno.util.SingletonApi
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){
    companion object{
        private lateinit var retrofit: Retrofit
        private const val BASE_URL = "https://siga.cps.sp.gov.br/"

        private fun getRetrofitIntance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val request = chain.request()
                val url = request.url().url()
                    .toString()
                    .replace(SingletonApi.URL_SIGA,SingletonApi.URL_API)
                when(SingletonApi.destino){
                    SingletonApi.API -> {
                        val build = Request.Builder()
                            .url(url)
                            .headers(request.headers())

                        if(request.method().lowercase() == "post"){
                            request.body()?.let { build.post(it) }
                        }

                        if (request.method().lowercase() == "get"){
                            build.get()
                        }

                        if(request.method().lowercase() == "patch"){
                            request.body()?.let { build.patch(it) }
                        }

                        chain.proceed(build.build())
                    }
                    else ->{
                        chain.proceed(request)
                    }
                }
            }

            if (!Companion::retrofit.isInitialized){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S{
            return getRetrofitIntance().create(serviceClass)
        }
    }

}