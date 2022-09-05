package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.adapters.HorarioAdapter
import com.fatec.sul.appdoaluno.databinding.FragmentHorarioBinding
import com.fatec.sul.appdoaluno.factories.HorarioViewModelFactory
import com.fatec.sul.appdoaluno.factories.MateriaViewModelFactory
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import com.fatec.sul.appdoaluno.viewmodel.HorarioViewModel

class HorarioFragment : Fragment(R.layout.fragment_horario){
    private lateinit var mHorarioViewModel: HorarioViewModel
    private lateinit var mBinding: FragmentHorarioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHorarioBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHorarioViewModel = ViewModelProvider(
            this,
            HorarioViewModelFactory(MateriaRepository(requireContext()))
        )[HorarioViewModel::class.java]

        mHorarioViewModel.buscarHorario()

        val horaioAdapter = HorarioAdapter()
        val recyclerHorario = mBinding.recyclerHorario
        recyclerHorario.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        recyclerHorario.adapter = horaioAdapter

        mHorarioViewModel.horarios.observe(viewLifecycleOwner){ horarios ->
            horaioAdapter.add(horarios)
        }

    }
}