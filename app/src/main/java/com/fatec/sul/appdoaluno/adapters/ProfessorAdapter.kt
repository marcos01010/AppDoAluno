package com.fatec.sul.appdoaluno.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.model.Professor

class ProfessorAdapter(private val professores : List<Professor>) : Adapter<ProfessorAdapter.ProfessorViewHolder>(){
    class ProfessorViewHolder(itemView: View) : ViewHolder(itemView) {
        fun binding(professor: Professor){
            val nome = itemView.findViewById<TextView>(R.id.txNomeProfessor)
            val materias = itemView.findViewById<TextView>(R.id.txMaterias)

            nome.text = professor.nome
            materias.text = professor.materia.joinToString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorViewHolder {
        return ProfessorViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_professor,parent,false))
    }

    override fun onBindViewHolder(holder: ProfessorViewHolder, position: Int) {
        holder.binding(professores[position])
    }

    override fun getItemCount(): Int {
        return professores.size
    }
}