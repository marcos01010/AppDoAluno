package com.fatec.sul.appdoaluno.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.model.api.Chamada
import java.util.function.Consumer

class ChamadaAdapter(private val chamadas: List<Chamada>, private val onResposta: Consumer<Long>) : Adapter<ChamadaAdapter.ChamadaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChamadaViewHolder {
        return ChamadaViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_chamada,parent,false))
    }

    override fun onBindViewHolder(holder: ChamadaViewHolder, position: Int) {
        holder.bindData(chamadas[position],onResposta)
    }

    override fun getItemCount(): Int {
        return chamadas.size
    }

    class ChamadaViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bindData(chamada: Chamada, onResposta: Consumer<Long>){
            val nomeMateria = itemView.findViewById<TextView>(R.id.txNomeMateira)
            val professor = itemView.findViewById<TextView>(R.id.txProfessor)
            val imgRespostaChamada = itemView.findViewById<ImageView>(R.id.imgRespostaChamada)

            nomeMateria.text = chamada.atividade.materia.descricao
            "${chamada.professor.nome} ${chamada.professor.sobreNome}".also { professor.text = it }
            imgRespostaChamada.setOnClickListener {
                onResposta.accept(chamada.id)
            }
        }
    }
}