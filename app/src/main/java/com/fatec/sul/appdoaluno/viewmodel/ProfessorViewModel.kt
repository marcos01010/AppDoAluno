package com.fatec.sul.appdoaluno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatec.sul.appdoaluno.model.Professor
import com.fatec.sul.appdoaluno.model.api.Usuario
import com.fatec.sul.appdoaluno.repository.ProfessorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfessorViewModel(private val professorRepository: ProfessorRepository): ViewModel() {
    val professores = MutableLiveData<List<Professor>>()

    fun buscarProfessores(){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                professorRepository.buscarProfessores()
            }
            professores.value = resultado
        }
    }
}