package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.databinding.FragmentInfoMateriaProfessorBinding
import com.fatec.sul.appdoaluno.databinding.FragmentMateriaProfessorBinding

class InforMateriaProfessorFragment : Fragment(R.layout.fragment_info_materia_professor){
    private lateinit var mBinding: FragmentInfoMateriaProfessorBinding

    private val args : InforMateriaProfessorFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentInfoMateriaProfessorBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        mBinding.txNomeMateiraInfo.text = args.materia.descricao
    }
}