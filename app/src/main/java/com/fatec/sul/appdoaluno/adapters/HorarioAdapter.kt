package com.fatec.sul.appdoaluno.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.model.Horario

class HorarioAdapter : Adapter<HorarioAdapter.HorarioViewHolder>() {
    private var horarios = listOf<Horario>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
        return HorarioViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_horario,parent,false))
    }

    override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
        holder.bindData(horarios[position])
    }

    override fun getItemCount(): Int {
        return horarios.size
    }

    fun add(materias: List<Horario>){
        this.horarios = materias
        notifyDataSetChanged()
    }

    class HorarioViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bindData(horaio: Horario){
            val sigla = itemView.findViewById<TextView>(R.id.txSiglaHorario)
            val horario = itemView.findViewById<TextView>(R.id.txHorario)
            val diaSemana = itemView.findViewById<TextView>(R.id.txDiaSemana)

            sigla.text = horaio.sigla
            horario.text = horaio.hora
            diaSemana.text = horaio.diaDaSemana
        }
    }
}