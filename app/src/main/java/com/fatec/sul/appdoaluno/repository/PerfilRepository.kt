package com.fatec.sul.appdoaluno.repository

import android.content.Context
import com.fatec.sul.appdoaluno.model.Aluno
import com.fatec.sul.appdoaluno.model.Login
import com.fatec.sul.appdoaluno.services.local.DataBase
import com.fatec.sul.appdoaluno.services.remote.FatecService
import com.fatec.sul.appdoaluno.util.Document
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PerfilRepository(context: Context){
    private val mRemote = RetrofitClient.createService(FatecService::class.java)
    private val alunoDao = DataBase.getDatabase(context).alunoDao()
    private val materiaDao = DataBase.getDatabase(context).materiaDao()
    private val horarioDao = DataBase.getDatabase(context).horarioDao()

    private fun getCokie():Login{
        val response = mRemote.home().execute()
        return Login().also {
            it.gxState = Document.getBaseGx(String(response.body()!!.bytes()))
            it.cookie = response.raw().headers().value(5).split(";")[0]
        }
    }

    private fun logar(login: Login): Boolean{
        try {
            val response = mRemote.logar(
                login.vSIS_USUARIOID,
                login.vSIS_USUARIOSENHA,
                login.BTCONFIRMA,
                login.gxState,
                login.cookie).execute().body()!!.bytes()

            val aluno = Document.getDados(String(response)) ?: return false
            alunoDao.salvar(aluno)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    private fun getMaterias(cookie: String) :Boolean{
        return try {
            var response = mRemote.materias(cookie).execute().body() ?: return false
            var html = String(response.bytes())
            val materias = Document.getMaterias(html)

            response = mRemote.horario(cookie).execute().body() ?: return false
            html = String(response.bytes())
            val dadosProfessores = Document.getProfessor(html)
            val horarios = Document.getHoraio(html)

            dadosProfessores.forEach { professor ->
                materias.filter { materia ->
                    professor[0] == materia.sigla
                }.forEach { materia ->
                    materia.apply{
                        this.professor = professor[3]
                    }
                }
            }

            horarioDao.save(horarios)
            materiaDao.save(materias)
            true
        } catch (e:Exception){
            e.printStackTrace()
            false
        }
    }

    suspend fun autenticarSiga(usuario:String, senha:String): Boolean{
        return withContext(Dispatchers.Default){
            val login = getCokie().also {
                it.vSIS_USUARIOID = usuario
                it.vSIS_USUARIOSENHA = senha
            }

            logar(login) && getMaterias(login.cookie)
        }
    }

    suspend fun buscarAluno():Aluno?{
        return withContext(Dispatchers.Default){
            val alunos = alunoDao.buscarTodos()
            //TODO TRATAR MELHOR ISSO
            if (alunos.isNullOrEmpty()){
                null
            }else{
                alunos[0]
            }
        }
    }
}