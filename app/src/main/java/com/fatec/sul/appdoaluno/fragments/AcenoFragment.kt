package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.adapters.AcenoAdapter
import com.fatec.sul.appdoaluno.databinding.FragmentAcenoBinding
import com.fatec.sul.appdoaluno.factories.AcenoViewModelFactory
import com.fatec.sul.appdoaluno.model.api.SalaApi
import com.fatec.sul.appdoaluno.repository.AcenoRepository
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import com.fatec.sul.appdoaluno.viewmodel.AcenoViewModel

class AcenoFragment : Fragment(R.layout.fragment_aceno){
    private lateinit var mBinding: FragmentAcenoBinding
    private lateinit var mAcenoViewModel: AcenoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAcenoBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mAcenoViewModel.pausarBusca()
    }

    override fun onPause() {
        super.onPause()
        mAcenoViewModel.pausarBusca()
    }

    override fun onResume() {
        super.onResume()
        mAcenoViewModel.iniciarBusca()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var salas = listOf<SalaApi>()
        var materias = listOf<com.fatec.sul.appdoaluno.model.Materia>()
        mAcenoViewModel = ViewModelProvider(
            this,
            AcenoViewModelFactory(AcenoRepository(requireContext()),MateriaRepository(requireContext()))
        )[AcenoViewModel::class.java]

        mAcenoViewModel.listarChamadas()
        mAcenoViewModel.buscarMaterias()
        mAcenoViewModel.buscarAcenos()

        mAcenoViewModel.salas.observe(viewLifecycleOwner){
            salas = it
            mBinding.spLocal.adapter = ArrayAdapter(requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                it.map { local ->  "Prédio: ${local.descricaoPredio} Sala: ${local.numero}" })
        }

        mAcenoViewModel.materias.observe(viewLifecycleOwner){
            materias = it
            mBinding.spMateriaAceno.adapter = ArrayAdapter(requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                it.map { materia ->  materia.descricao })
        }

        mAcenoViewModel.acenoConfirmado.observe(viewLifecycleOwner){
            mAcenoViewModel.buscarAcenos()
        }

        mAcenoViewModel.acenos.observe(viewLifecycleOwner){
            val acenoAdapter = AcenoAdapter(it){ acenoID ->
                mAcenoViewModel.confirmarAceno(acenoID)
            }
            val recyclerAcenos = mBinding.recyclerAcenos
            recyclerAcenos.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
            recyclerAcenos.adapter = acenoAdapter
        }

        mAcenoViewModel.acenoCriado.observe(viewLifecycleOwner){
            mAcenoViewModel.buscarAcenos()
        }

        mBinding.btnCriarAceno.setOnClickListener {
            val sala = salas[mBinding.spLocal.selectedItemPosition].id
            val materia = materias[mBinding.spMateriaAceno.selectedItemPosition]
            val descricao = mBinding.etxDescricaoAceno.text.toString()

            mAcenoViewModel.criarAceno(sala,descricao,materia)
        }

    }
}