package com.fatec.sul.appdoaluno.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatec.sul.appdoaluno.repository.PerfilRepository
import com.fatec.sul.appdoaluno.viewmodel.PerfilViewModel

class PerfilViewModelFactory(
        private val perfilRepository: PerfilRepository
    ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PerfilViewModel(perfilRepository) as T
    }
}