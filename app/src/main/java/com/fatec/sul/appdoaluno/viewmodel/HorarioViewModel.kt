package com.fatec.sul.appdoaluno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatec.sul.appdoaluno.model.Horario
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import kotlinx.coroutines.*

class HorarioViewModel(private val materiaRepository: MateriaRepository) : ViewModel() {
    val horarios = MutableLiveData<List<Horario>>()
    fun buscarHorario() {
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                materiaRepository.buscarHorarios()
            }
            horarios.value = resultado
        }
    }
}