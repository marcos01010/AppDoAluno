package com.fatec.sul.appdoaluno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatec.sul.appdoaluno.model.Materia
import com.fatec.sul.appdoaluno.model.api.Chamada
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import kotlinx.coroutines.*

class MateriaViewModel(private val materiaRepository: MateriaRepository) : ViewModel() {
    val materias = MutableLiveData<List<Materia>>()
    val materiasProfessor = MutableLiveData<List<com.fatec.sul.appdoaluno.model.api.Materia>>()
    val materiasAssumidas = MutableLiveData<List<com.fatec.sul.appdoaluno.model.api.Materia>>()
    val assumirMateria = MutableLiveData<Boolean>()
    val chamadaCriada = MutableLiveData<Boolean>()

    fun buscarMaterias() {
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                materiaRepository.buscarMaterias()
            }
            materias.value = resultado
        }
    }

    fun buscarMateriasProfessor() {
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                materiaRepository.buscarMateriasProfessor()
            }
            materiasProfessor.value = resultado
        }
    }

    fun buscarMateriasProfessor(hashChamada: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                materiaRepository.buscarMateriasProfessor(hashChamada)
            }
            materiasAssumidas.value = resultado
        }
    }

    fun assumirMateria(materia: com.fatec.sul.appdoaluno.model.api.Materia){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                materiaRepository.assumirMateria(materia)
            }
            assumirMateria.value = resultado
        }
    }

    fun abrirChamada(chamada: Chamada) {
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                materiaRepository.abrirChamada(chamada)
            }
            chamadaCriada.value = resultado
        }
    }
}