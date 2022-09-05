package com.fatec.sul.appdoaluno.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.model.Materia

class MateriaAdapter : Adapter<MateriaAdapter.MateriaViewHolder>() {
    private var materias = listOf<Materia>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaViewHolder {
        return MateriaViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_materia,parent,false))
    }

    override fun onBindViewHolder(holder: MateriaViewHolder, position: Int) {
        holder.bindData(materias[position])
    }

    override fun getItemCount(): Int {
        return materias.size
    }

    fun add(materias: List<Materia>){
        this.materias = materias
        notifyDataSetChanged()
    }

    class MateriaViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bindData(materia: Materia){
            val sigla = itemView.findViewById<TextView>(R.id.txSiglaMateria)
            val descricao = itemView.findViewById<TextView>(R.id.txDescricaoMateria)
            val professor = itemView.findViewById<TextView>(R.id.txProfessor)
            val nota = itemView.findViewById<TextView>(R.id.txNota)
            val frequencia = itemView.findViewById<TextView>(R.id.txFrequencia)

            sigla.text = materia.sigla
            descricao.text = materia.descricao
            nota.text = materia.media.toString()
            professor.text = materia.professor
            frequencia.text = materia.frquencia.toString()
        }
    }
}