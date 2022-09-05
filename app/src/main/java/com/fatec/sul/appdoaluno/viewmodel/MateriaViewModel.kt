package com.fatec.sul.appdoaluno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatec.sul.appdoaluno.model.Materia
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import kotlinx.coroutines.*

class MateriaViewModel(private val materiaRepository: MateriaRepository) : ViewModel() {
    val materias = MutableLiveData<List<Materia>>()
    fun buscarMaterias() {
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                materiaRepository.buscarMaterias()
            }
            materias.value = resultado
        }
    }
}