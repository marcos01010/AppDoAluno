package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.databinding.FragmentInfoMateriaProfessorBinding
import com.fatec.sul.appdoaluno.databinding.FragmentMateriaProfessorBinding
import com.fatec.sul.appdoaluno.factories.MateriaViewModelFactory
import com.fatec.sul.appdoaluno.factories.ProfessorViewModelFacroty
import com.fatec.sul.appdoaluno.repository.ChamadaRepository
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import com.fatec.sul.appdoaluno.repository.ProfessorRepository
import com.fatec.sul.appdoaluno.viewmodel.MateriaViewModel
import com.fatec.sul.appdoaluno.viewmodel.ProfessorViewModel

class InforMateriaProfessorFragment : Fragment(R.layout.fragment_info_materia_professor){
    private lateinit var mBinding: FragmentInfoMateriaProfessorBinding
    private lateinit var mMateriaViewModel: MateriaViewModel

    private val args : InforMateriaProfessorFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentInfoMateriaProfessorBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMateriaViewModel = ViewModelProvider(
            this,
            MateriaViewModelFactory(MateriaRepository(requireContext()), ChamadaRepository(requireContext()))
        )[MateriaViewModel::class.java]

        mBinding.btnSolicitarControle.setOnClickListener {
            mMateriaViewModel.assumirMateria(args.materia)
        }

        mMateriaViewModel.assumirMateria.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(requireContext(), "Sucesso!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "Não foi possível completar a operação", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mBinding.txNomeMateiraInfo.text = args.materia.descricao
    }
}