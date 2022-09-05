package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.adapters.MateriaAdapter
import com.fatec.sul.appdoaluno.databinding.FragmentMateriaBinding
import com.fatec.sul.appdoaluno.factories.MateriaViewModelFactory
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import com.fatec.sul.appdoaluno.viewmodel.MateriaViewModel

class MateriaFragment : Fragment(R.layout.fragment_materia) {
    private lateinit var mMateriaViewModel: MateriaViewModel
    private lateinit var mBinding: FragmentMateriaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMateriaBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMateriaViewModel = ViewModelProvider(
            this,
            MateriaViewModelFactory(MateriaRepository(requireContext()))
        )[MateriaViewModel::class.java]

        mMateriaViewModel.buscarMaterias()

        val materiaAdapter = MateriaAdapter()
        val recyclerMateria = mBinding.recyclerMaterias
        recyclerMateria.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recyclerMateria.adapter = materiaAdapter

        mMateriaViewModel.materias.observe(viewLifecycleOwner){materias ->
            materiaAdapter.add(materias)
        }

    }
}
