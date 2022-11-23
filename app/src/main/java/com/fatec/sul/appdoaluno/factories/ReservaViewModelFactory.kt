package com.fatec.sul.appdoaluno.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatec.sul.appdoaluno.repository.ReservaRepository
import com.fatec.sul.appdoaluno.viewmodel.ReservaViewModel

class ReservaViewModelFactory(private val reservaRespository: ReservaRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReservaViewModel(reservaRespository) as T
    }
}