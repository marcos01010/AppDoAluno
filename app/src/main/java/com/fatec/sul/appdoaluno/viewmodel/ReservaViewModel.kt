package com.fatec.sul.appdoaluno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatec.sul.appdoaluno.model.api.Reserva
import com.fatec.sul.appdoaluno.model.api.SalaApi
import com.fatec.sul.appdoaluno.repository.ReservaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservaViewModel(private val reservaRepository: ReservaRepository) :ViewModel() {
    val reservas = MutableLiveData<List<Reserva>>()
    val salas = MutableLiveData<List<SalaApi>>()
    val isReservado = MutableLiveData<Boolean>()

    fun buscarReservas(){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                reservaRepository.buscarReservas()
            }
            reservas.value = resultado
        }
    }

    fun listarLocais(){
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                reservaRepository.buscarLocais()
            }
            salas.value = resultado
        }
    }

    fun reservar(reserva: Reserva) {
        CoroutineScope(Dispatchers.Main).launch {
            val resultado = withContext(Dispatchers.Default){
                reservaRepository.reservar(reserva)
            }
            isReservado.value = resultado
        }
    }

}