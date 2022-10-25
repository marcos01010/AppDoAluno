package com.fatec.sul.appdoaluno.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatec.sul.appdoaluno.repository.ChamadaRepository
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import com.fatec.sul.appdoaluno.viewmodel.MateriaViewModel

class MateriaViewModelFactory (
        private val materiaRepository: MateriaRepository,
        private val chamadaRepository: ChamadaRepository
    ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MateriaViewModel(materiaRepository,chamadaRepository) as T
    }
}