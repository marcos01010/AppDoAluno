package com.fatec.sul.appdoaluno.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.model.api.Usuario

class AlunosPresenteAdapter(private val alunos : List<Usuario>) : Adapter<AlunosPresenteAdapter.AlunosPresentesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunosPresentesViewHolder {
        return AlunosPresentesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_simples,parent,false))
    }

    override fun onBindViewHolder(holder: AlunosPresentesViewHolder, position: Int) {
        holder.biding(alunos[position])
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

    class AlunosPresentesViewHolder(itemView: View) : ViewHolder(itemView) {
        fun biding(aluno : Usuario){
            itemView.findViewById<TextView>(R.id.txInformacao).text = aluno.nome
        }
    }
}