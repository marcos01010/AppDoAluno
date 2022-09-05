package com.fatec.sul.appdoaluno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatec.sul.appdoaluno.model.Aluno
import com.fatec.sul.appdoaluno.repository.PerfilRepository
import kotlinx.coroutines.*

class PerfilViewModel(private val perfilRepository: PerfilRepository): ViewModel() {
    val autenticacao = MutableLiveData<Boolean>()
    val aluno = MutableLiveData<Aluno?>()

    fun autenticarSiga(usuario: String, senha: String){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default) {
                perfilRepository.autenticarSiga(usuario,senha)
            }
            autenticacao.value = resultado
        }
    }

    fun buscarAluno(){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                perfilRepository.buscarAluno()
            }
            aluno.value = resultado
        }
    }
}