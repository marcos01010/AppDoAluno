package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.adapters.ProfessorAdapter
import com.fatec.sul.appdoaluno.databinding.FragmentAcenoBinding
import com.fatec.sul.appdoaluno.databinding.FragmentProfessorBinding
import com.fatec.sul.appdoaluno.factories.AcenoViewModelFactory
import com.fatec.sul.appdoaluno.factories.ProfessorViewModelFacroty
import com.fatec.sul.appdoaluno.model.api.SalaApi
import com.fatec.sul.appdoaluno.repository.AcenoRepository
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import com.fatec.sul.appdoaluno.repository.ProfessorRepository
import com.fatec.sul.appdoaluno.viewmodel.AcenoViewModel
import com.fatec.sul.appdoaluno.viewmodel.ProfessorViewModel

class ProfessorFragment : Fragment(R.layout.fragment_professor){
    private lateinit var mBinding: FragmentProfessorBinding
    private lateinit var mProfessorViewModel: ProfessorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentProfessorBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         mProfessorViewModel = ViewModelProvider(
            this,
            ProfessorViewModelFacroty(ProfessorRepository(requireContext()))
            )[ProfessorViewModel::class.java]

        mProfessorViewModel.buscarProfessores()

        mProfessorViewModel.professores.observe(viewLifecycleOwner){
            val adapter = ProfessorAdapter(it)
            val recycler = mBinding.recyclerProfessor
            recycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            recycler.adapter = adapter
        }
    }
}