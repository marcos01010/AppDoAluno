package com.fatec.sul.appdoaluno.repository

import android.content.Context
import com.fatec.sul.appdoaluno.model.Aluno
import com.fatec.sul.appdoaluno.model.Horario
import com.fatec.sul.appdoaluno.model.Login
import com.fatec.sul.appdoaluno.model.api.Materia
import com.fatec.sul.appdoaluno.model.api.Perfil
import com.fatec.sul.appdoaluno.model.api.Usuario
import com.fatec.sul.appdoaluno.services.local.DataBase
import com.fatec.sul.appdoaluno.services.remote.ApiService
import com.fatec.sul.appdoaluno.services.remote.FatecService
import com.fatec.sul.appdoaluno.util.DataHora
import com.fatec.sul.appdoaluno.util.Document
import com.fatec.sul.appdoaluno.util.SingletonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.stream.Collectors

class PerfilRepository(context: Context){
    private val mRemote = RetrofitClient.createService(FatecService::class.java)
    private val mRemoteApi = RetrofitClient.createService(ApiService::class.java)
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
            val usuario = salvarAlunoAPI(aluno) ?: return false
            alunoDao.salvar(aluno.also { it.usuarioID = usuario.id })
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

            val horariosCadastrados = horarioDao.buscarTodos()

            val horariosNaoCadastrados = horarios.filter{ horario ->
                horariosCadastrados.none { horarioCadastrado ->
                        horarioCadastrado.sigla == horario.sigla
                                && horarioCadastrado.turno == horario.turno
                    }
            }

            horarioDao.save(horariosNaoCadastrados)
            materiaDao.save(materias)
            salvarMateriaAPI(materias)
            true
        } catch (e:Exception){
            e.printStackTrace()
            false
        }
    }

    suspend fun autenticarSiga(usuario:String, senha:String): Boolean{
        return withContext(Dispatchers.Default){
            alunoDao.deletarTodos()
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
            if (alunos.isEmpty()){
                null
            }else{
                alunos[0]
            }
        }
    }

    private fun salvarAlunoAPI(aluno: Aluno):Usuario?{
        SingletonApi.destino = SingletonApi.API//TODO melhorar transformação aluno para usuario
        val response = mRemoteApi.salvarAlunoApi(Usuario(
            0L,
            aluno.nome.split(" ")[0],
            aluno.nome.split(" ")[1],
            Perfil(2,""),
            "",
            aluno.ra)).execute()
        SingletonApi.destino = SingletonApi.SIGA
        
        return when(response.code()){
            200 -> {
                //.accept("Busca realizada com sucesso")
                response.body()
            }
            204 -> {
                //mensagem.accept("Nenhuma chamada aberta")
                response.body()
            }
            400 -> {
                //mensagem.accept("Erro 400: Contate o desenvolvedor ou tente novamente")
                null
            }
            500 -> {
                //mensagem.accept("Erro 500: Contate o desenvolvedor ou tente novamente mais tarde")
                null
            }
            else -> {
                //mensagem.accept("${response.code()}: Algo inesperado aconteceu")
                null
            }
        }
    }

    private fun salvarMateriaAPI(materias: List<com.fatec.sul.appdoaluno.model.Materia>):Boolean{
        SingletonApi.destino = SingletonApi.API
        val materiasApi = materias.map{ m ->
            Materia(m.sigla,m.descricao,null, DataHora.horaToTurno(horarioDao.buscarHorario(m.sigla)))
        }
        val response = mRemoteApi.salvarMateriaAPI(materiasApi).execute()
        SingletonApi.destino = SingletonApi.SIGA

        return when(response.code()){
            200 -> {
                //.accept("Busca realizada com sucesso")
                true
            }
            204 -> {
                //mensagem.accept("Nenhuma chamada aberta")
                true
            }
            400 -> {
                //mensagem.accept("Erro 400: Contate o desenvolvedor ou tente novamente")
                false
            }
            500 -> {
                //mensagem.accept("Erro 500: Contate o desenvolvedor ou tente novamente mais tarde")
                false
            }
            else -> {
                //mensagem.accept("${response.code()}: Algo inesperado aconteceu")
                false
            }
        }
    }
}