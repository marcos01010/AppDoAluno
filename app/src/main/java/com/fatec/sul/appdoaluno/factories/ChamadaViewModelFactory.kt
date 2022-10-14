package com.fatec.sul.appdoaluno.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatec.sul.appdoaluno.repository.ChamadaRepository
import com.fatec.sul.appdoaluno.viewmodel.ChamadaViewModel

class ChamadaViewModelFactory(private val chamadaRepository: ChamadaRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChamadaViewModel(chamadaRepository) as T
    }
}