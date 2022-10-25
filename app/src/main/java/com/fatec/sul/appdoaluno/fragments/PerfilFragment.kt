package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.databinding.FragmentPerfilBinding
import com.fatec.sul.appdoaluno.factories.PerfilViewModelFactory
import com.fatec.sul.appdoaluno.repository.PerfilRepository
import com.fatec.sul.appdoaluno.util.SingletonProfessor
import com.fatec.sul.appdoaluno.viewmodel.PerfilViewModel
import com.google.android.material.navigation.NavigationView

class PerfilFragment : Fragment(R.layout.fragment_perfil) {
    private lateinit var mBinding: FragmentPerfilBinding
    private lateinit var mPerfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentPerfilBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPerfilViewModel = ViewModelProvider(this,
            PerfilViewModelFactory(PerfilRepository(requireContext()))
        )[PerfilViewModel::class.java]

        mPerfilViewModel.buscarAluno()

        mBinding.btnAutenticaSiga.setOnClickListener {
            if(mBinding.btnAutenticaSiga.isEnabled){
                val usuario = mBinding.etxUsuario.text.toString()
                val senha = mBinding.etxSenha.text.toString()
                mPerfilViewModel.autenticarSiga(usuario,senha)
            }
        }

        mBinding.btnAutenticaProfessor.setOnClickListener{
            //TODO verificar no servidor
            SingletonProfessor.hashChamada = mBinding.etxAutenticaProfessor.text.toString()
            val menu = view.rootView.findViewById<NavigationView>(R.id.navigationView).menu

            menu.findItem(R.id.abrirChamadaFragment).isVisible = true
            menu.findItem(R.id.materiaProfessorFragment).isVisible = true

            menu.findItem(R.id.materiaFragment).isVisible = false
            menu.findItem(R.id.professorFragment).isVisible = false
            menu.findItem(R.id.chamadaFragment).isVisible = false
            menu.findItem(R.id.horarioFragment).isVisible = false

            Toast.makeText(context,"Bem vindo Professor", Toast.LENGTH_LONG).show()
        }

        mPerfilViewModel.aluno.observe(viewLifecycleOwner){ aluno ->
            if (aluno == null){
                mPerfilViewModel.autenticacao.observe(viewLifecycleOwner) { resultado ->
                    Toast.makeText(requireContext(), resultado.toString(), Toast.LENGTH_LONG).show()
                }
                mBinding.btnAutenticaSiga.isEnabled = true
            }else{
                mBinding.txNome.text = aluno.nome
                mBinding.txRa.text = aluno.ra.toString()
            }
        }
    }
}