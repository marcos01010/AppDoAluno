package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.adapters.AlunosPresenteAdapter
import com.fatec.sul.appdoaluno.databinding.FragmentAlunosBinding
import com.fatec.sul.appdoaluno.factories.ChamadaViewModelFactory
import com.fatec.sul.appdoaluno.repository.ChamadaRepository
import com.fatec.sul.appdoaluno.viewmodel.ChamadaViewModel

class AlunosFragment : Fragment(R.layout.fragment_alunos){
    private lateinit var mBinding: FragmentAlunosBinding
    private lateinit var mChamadaViewModel: ChamadaViewModel

    private val args : AlunosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAlunosBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mChamadaViewModel = ViewModelProvider(
            this,
            ChamadaViewModelFactory(ChamadaRepository(requireContext()))
        )[ChamadaViewModel::class.java]

        mChamadaViewModel.buscarPresentes(args.chamadaID)

        mChamadaViewModel.presentes.observe(viewLifecycleOwner){ alunos ->
            val recyclerAlunos = mBinding.recyclerAlunos
            recyclerAlunos.layoutManager =
                LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            recyclerAlunos.adapter = AlunosPresenteAdapter(alunos)
        }
    }

}