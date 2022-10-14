package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.adapters.ChamadaAdapter
import com.fatec.sul.appdoaluno.databinding.FragmentChamadaBinding
import com.fatec.sul.appdoaluno.factories.ChamadaViewModelFactory
import com.fatec.sul.appdoaluno.repository.ChamadaRepository
import com.fatec.sul.appdoaluno.viewmodel.ChamadaViewModel

class ChamadaFragment : Fragment(R.layout.fragment_chamada){
    private lateinit var mChamadaViewModel: ChamadaViewModel
    private lateinit var mBinding: FragmentChamadaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentChamadaBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mChamadaViewModel = ViewModelProvider(
            this,
            ChamadaViewModelFactory(ChamadaRepository(requireContext()))
        )[ChamadaViewModel::class.java]

        mChamadaViewModel.listarChamadas { resposta ->
            Toast.makeText(context, resposta, Toast.LENGTH_LONG).show()
        }

        mChamadaViewModel.chamadas.observe(viewLifecycleOwner){ chamadas ->
            val chamadaAdapter = ChamadaAdapter(chamadas){ chamadaID ->
                mChamadaViewModel.responderChamada(chamadaID)
            }
            val recyclerChamada = mBinding.recyclerChamadas
            recyclerChamada.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
            recyclerChamada.adapter = chamadaAdapter
        }
    }

}