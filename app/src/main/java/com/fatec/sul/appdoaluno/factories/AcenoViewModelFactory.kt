package com.fatec.sul.appdoaluno.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatec.sul.appdoaluno.repository.AcenoRepository
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import com.fatec.sul.appdoaluno.viewmodel.AcenoViewModel
import com.fatec.sul.appdoaluno.viewmodel.ChamadaViewModel

class AcenoViewModelFactory(private val acenoRepository: AcenoRepository, private val materiaRepository: MateriaRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AcenoViewModel(materiaRepository,acenoRepository) as T
    }
}