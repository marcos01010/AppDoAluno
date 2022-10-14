package com.fatec.sul.appdoaluno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatec.sul.appdoaluno.model.api.Chamada
import com.fatec.sul.appdoaluno.repository.ChamadaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.function.Consumer

class ChamadaViewModel(private val chamadaRepository: ChamadaRepository): ViewModel() {
    val chamadas = MutableLiveData<List<Chamada>>()
    val isChamadaRespondida = MutableLiveData<Boolean>()

    fun listarChamadas(mensagem: Consumer<String>){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                chamadaRepository.listarChamadas(mensagem)
            }
            chamadas.value = resultado
        }
    }

    fun responderChamada(chamadaID :Long){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                chamadaRepository.responderChamada(chamadaID)
            }
            isChamadaRespondida.value = resultado
        }
    }
}