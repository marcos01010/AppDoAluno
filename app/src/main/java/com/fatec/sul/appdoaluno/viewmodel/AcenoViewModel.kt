package com.fatec.sul.appdoaluno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatec.sul.appdoaluno.model.Materia
import com.fatec.sul.appdoaluno.model.api.SalaApi
import com.fatec.sul.appdoaluno.model.api.delivery.AcenoDelivery
import com.fatec.sul.appdoaluno.repository.AcenoRepository
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import kotlinx.coroutines.*

class AcenoViewModel (private val materiaRepository: MateriaRepository, private val acenoRepository: AcenoRepository): ViewModel() {
    val salas = MutableLiveData<List<SalaApi>>()
    val acenoCriado = MutableLiveData<Boolean>()
    val acenoConfirmado = MutableLiveData<Boolean>()
    val materias = MutableLiveData<List<Materia>>()
    val acenos = MutableLiveData<List<AcenoDelivery>>()
    private var isResume = true;

    fun pausarBusca(){
        isResume = false;
    }

    fun iniciarBusca(){
        isResume = true;
    }

    fun listarChamadas(){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                acenoRepository.buscarLocais()
            }
            salas.value = resultado
        }
    }

    fun criarAceno(sala: Int, descricao: String,materia: Materia) {
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                acenoRepository.criarAceno(sala, descricao,materia)
            }
            acenoCriado.value = resultado
        }
    }

    fun buscarAcenos() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default){
                isResume = true
                while (isResume){
                    val list = acenoRepository.buscarAcenos()
                    acenos.postValue(list)
                    delay(30000)
                }
            }
        }
    }

    fun buscarMaterias() {
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                materiaRepository.buscarMaterias()
            }
            materias.value = resultado
        }
    }

    fun confirmarAceno(id:Long){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                acenoRepository.confirmarAceno(id)
            }
            acenoConfirmado.value = resultado
        }
    }
}