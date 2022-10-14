package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.adapters.MateriaProfessorAdapter
import com.fatec.sul.appdoaluno.databinding.FragmentMateriaProfessorBinding
import com.fatec.sul.appdoaluno.factories.MateriaViewModelFactory
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import com.fatec.sul.appdoaluno.viewmodel.MateriaViewModel

class MateriaProfessorFragment : Fragment(R.layout.fragment_materia_professor){
    private lateinit var mMateriaViewModel: MateriaViewModel
    private lateinit var mBinding: FragmentMateriaProfessorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMateriaProfessorBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMateriaViewModel = ViewModelProvider(
            this,
            MateriaViewModelFactory(MateriaRepository(requireContext()))
        )[MateriaViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        mMateriaViewModel.buscarMateriasProfessor()
        mMateriaViewModel.materiasProfessor.observe(viewLifecycleOwner){
            val materiaProfessorAdapter = MateriaProfessorAdapter(it,requireContext()){materia->
                mMateriaViewModel.assumirMateria(materia)
            }
            val recyclerMateriasProfessor = mBinding.recyclerMateriasProfessor
            recyclerMateriasProfessor.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            recyclerMateriasProfessor.adapter = materiaProfessorAdapter
        }
        mMateriaViewModel.assumirMateria.observe(viewLifecycleOwner){
            mMateriaViewModel.buscarMateriasProfessor()
        }
    }


}