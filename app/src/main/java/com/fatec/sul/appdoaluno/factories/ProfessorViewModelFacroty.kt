package com.fatec.sul.appdoaluno.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatec.sul.appdoaluno.repository.ProfessorRepository
import com.fatec.sul.appdoaluno.viewmodel.ProfessorViewModel

class ProfessorViewModelFacroty(private val professorRepository: ProfessorRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfessorViewModel(professorRepository) as T
    }
}