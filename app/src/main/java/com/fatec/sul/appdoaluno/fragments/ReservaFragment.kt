package com.fatec.sul.appdoaluno.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.adapters.ReservaAdapter
import com.fatec.sul.appdoaluno.databinding.FragmentReservaBinding
import com.fatec.sul.appdoaluno.factories.ReservaViewModelFactory
import com.fatec.sul.appdoaluno.model.api.Reserva
import com.fatec.sul.appdoaluno.model.api.SalaApi
import com.fatec.sul.appdoaluno.repository.ReservaRepository
import com.fatec.sul.appdoaluno.viewmodel.ReservaViewModel
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ReservaFragment:Fragment(R.layout.fragment_reserva) {
    private lateinit var mBinding: FragmentReservaBinding
    private lateinit var mReservaViewModel: ReservaViewModel
    private val calendar = Calendar.getInstance()
    private var reservaDe = Calendar.getInstance()
    private var reservaAte = Calendar.getInstance()
    private var salas = listOf<SalaApi>()
    private val df = SimpleDateFormat("dd MMMM yyyy hh:mm")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentReservaBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mReservaViewModel = ViewModelProvider(
            this,
            ReservaViewModelFactory(ReservaRepository(requireContext()))
        )[ReservaViewModel::class.java]


        mReservaViewModel.buscarReservas()
        mReservaViewModel.reservas.observe(viewLifecycleOwner){
            val recycler = mBinding.recyclerReserva
            recycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            recycler.adapter = ReservaAdapter(it)
        }

        mBinding.txReservaDe.setOnClickListener {
            DatePickerDialog(requireContext(), { _, ano, mes, dia ->
                reservaDe.set(Calendar.YEAR, ano)
                reservaDe.set(Calendar.MONTH, mes)
                reservaDe.set(Calendar.DAY_OF_MONTH, dia)
                TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, hora, minuto ->
                    reservaDe.set(Calendar.HOUR, hora)
                    reservaDe.set(Calendar.MINUTE, minuto)
                    reservaDe.set(Calendar.SECOND, 0)
                    mBinding.txReservaDe.setText(df.format(reservaDe.time))
                },calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),true).show()
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        mBinding.txReservaAte.setOnClickListener {
            DatePickerDialog(requireContext(), { _, ano, mes, dia ->
                reservaAte.set(Calendar.YEAR, ano)
                reservaAte.set(Calendar.MONTH, mes)
                reservaAte.set(Calendar.DAY_OF_MONTH, dia)
                TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, hora, minuto ->
                    reservaAte.set(Calendar.HOUR, hora)
                    reservaAte.set(Calendar.MINUTE, minuto)
                    reservaAte.set(Calendar.SECOND, 0)

                    mBinding.txReservaAte.setText(df.format(reservaAte.time))
                },calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),true).show()
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        mReservaViewModel.listarLocais()
        mReservaViewModel.salas.observe(viewLifecycleOwner){
            salas = it
            mBinding.spLocal.adapter = ArrayAdapter(requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                it.map { local ->  "${local.descricaoPredio}: ${local.descricaoSala} - ${local.numero}" })
        }

        mBinding.btnReservar.setOnClickListener {
            val incio = ZonedDateTime
                .ofInstant(reservaDe.time.toInstant(), TimeZone.getDefault().toZoneId())
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME).toString()
            val fim = ZonedDateTime
                .ofInstant(reservaAte.time.toInstant(), TimeZone.getDefault().toZoneId())
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME).toString()

            val salaSelecionada = mBinding.spLocal.selectedItemPosition

            val reserva = Reserva(inicio = incio, fim = fim, numeroSala =  salas[salaSelecionada].numero, salaID = salas[salaSelecionada].id)

            mReservaViewModel.reservar(reserva)
        }

        mReservaViewModel.isReservado.observe(viewLifecycleOwner){
            mReservaViewModel.buscarReservas()
            if(it){
                Toast.makeText(context,"Reserva Efetuada com sucesso!",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Não foi possível reservar!",Toast.LENGTH_LONG).show()
            }
        }

    }
}