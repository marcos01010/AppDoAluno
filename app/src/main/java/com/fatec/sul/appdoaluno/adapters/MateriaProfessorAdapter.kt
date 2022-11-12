package com.fatec.sul.appdoaluno.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.model.api.Materia
import com.fatec.sul.appdoaluno.model.api.Perfil
import com.fatec.sul.appdoaluno.model.api.Usuario
import com.fatec.sul.appdoaluno.util.SingletonProfessor
import java.util.function.Consumer

class MateriaProfessorAdapter(val materias: List<Materia>, val context: Context, private val listener: Consumer<Materia>):
    Adapter<MateriaProfessorAdapter.MateriaProfessorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaProfessorViewHolder {
        return MateriaProfessorViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.row_materia_professor,parent,false))
    }

    override fun onBindViewHolder(holder: MateriaProfessorViewHolder, position: Int) {
        holder.bindData(materias[position], context, listener)
    }

    override fun getItemCount(): Int {
        return materias.size
    }

    class MateriaProfessorViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bindData(materia: Materia, context: Context, listener: Consumer<Materia>){
            val sigla = itemView.findViewById<TextView>(R.id.txSiglaMateriaProfessor)
            val descricao = itemView.findViewById<TextView>(R.id.txNomeMateiraProfessor)
            val professor = itemView.findViewById<TextView>(R.id.txNomeProfessorMateriaProfessor)
            val recycler = itemView.findViewById<ConstraintLayout>(R.id.constraintMateriaProfessor)

            sigla.text = materia.sigla
            descricao.text = materia.descricao
            if (materia == null){
                professor.text = context.getString(R.string.sem_professor_declarado)
            }
            materia.professor?.let {
                val nomeCompleto = "${it.nome} ${it.sobreNome}"
                professor.text = nomeCompleto
            }

            recycler.setOnClickListener {
                listener.accept(materia)
            }
        }
    }
}