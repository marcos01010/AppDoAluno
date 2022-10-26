package com.fatec.sul.appdoaluno.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.adapters.ChamadaAbertaAdapter
import com.fatec.sul.appdoaluno.databinding.FragmentAbrirChamadaBinding
import com.fatec.sul.appdoaluno.factories.MateriaViewModelFactory
import com.fatec.sul.appdoaluno.model.api.Atividade
import com.fatec.sul.appdoaluno.model.api.Chamada
import com.fatec.sul.appdoaluno.model.api.Materia
import com.fatec.sul.appdoaluno.model.api.Sala
import com.fatec.sul.appdoaluno.repository.ChamadaRepository
import com.fatec.sul.appdoaluno.repository.MateriaRepository
import com.fatec.sul.appdoaluno.util.DataHora
import com.fatec.sul.appdoaluno.util.SingletonProfessor
import com.fatec.sul.appdoaluno.viewmodel.MateriaViewModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AbrirChamadaFragment : Fragment(R.layout.fragment_abrir_chamada){
    private lateinit var mBinding: FragmentAbrirChamadaBinding
    private lateinit var mMateriaViewModel: MateriaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAbrirChamadaBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var materias = listOf<Materia>()
        mMateriaViewModel = ViewModelProvider(
            this,
            MateriaViewModelFactory(MateriaRepository(requireContext()), ChamadaRepository(requireContext()))
        )[MateriaViewModel::class.java]

        mMateriaViewModel.buscarMateriasProfessor(SingletonProfessor.hashChamada)
        mMateriaViewModel.materiasAssumidas.observe(viewLifecycleOwner){
            materias = it
            mBinding.spMateria.adapter = ArrayAdapter(requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                it.map { materia ->  materia.descricao })
        }

        mMateriaViewModel.chamadaCriada.observe(viewLifecycleOwner){
            mMateriaViewModel.buscarChamadas(SingletonProfessor.hashChamada)
            if(it){
                Toast.makeText(context,"Chamada criada",Toast.LENGTH_LONG).show()
            }
        }

        mMateriaViewModel.buscarChamadas(SingletonProfessor.hashChamada)

        mMateriaViewModel.chamadas.observe(viewLifecycleOwner){ chamadas ->
            val chamadaAbertaAdapter = ChamadaAbertaAdapter(chamadas){chamadaID ->
                val navController = Navigation.findNavController(view)
                navController.navigate(AbrirChamadaFragmentDirections
                    .actionAbrirChamadaFragmentToAlunosFragment(chamadaID))
            }
            val recyclerChamadasAbertas = mBinding.recyclerChamadasAbertas
            recyclerChamadasAbertas.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
            recyclerChamadasAbertas.adapter = chamadaAbertaAdapter
        }

        mBinding.btnAbrirChamada.setOnClickListener {
            val sala: Sala? = null
            val materia = materias[mBinding.spMateria.selectedItemPosition]
            val professor = materias[mBinding.spMateria.selectedItemPosition].professor
            val atividade = Atividade(0L,materia, sala)
            if(professor != null && professor.hashChamada == SingletonProfessor.hashChamada &&
                materia.sigla.isNotEmpty()){
                val tempo = mBinding.txTempoChamada.text.toString()
                val calendar = Calendar.getInstance(TimeZone.getDefault())
                calendar.add(Calendar.MINUTE, DataHora.minutesOf(tempo))
                val tempoZona = ZonedDateTime
                    .ofInstant(calendar.time.toInstant(), TimeZone.getDefault().toZoneId())
                    .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME).toString()
                mMateriaViewModel.abrirChamada(Chamada(0L,atividade, professor, tempoZona))
                mMateriaViewModel.buscarChamadas(SingletonProfessor.hashChamada)
            }else{
                Toast.makeText(context,"Não foi possível abrir a chamada",Toast.LENGTH_LONG).show()
            }
        }
    }
}