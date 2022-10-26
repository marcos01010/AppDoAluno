package com.fatec.sul.appdoaluno.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.model.api.Chamada
import java.util.function.Consumer

class ChamadaAbertaAdapter(private val chamadasAbertas : List<Chamada>, private val onClickListerner : Consumer<Long>) : Adapter<ChamadaAbertaAdapter.ChamadaAbertaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChamadaAbertaViewHolder {
        return ChamadaAbertaViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.row_chamada_aberta,parent,false))
    }

    override fun onBindViewHolder(holder: ChamadaAbertaViewHolder, position: Int) {
        holder.biding(chamadasAbertas[position], onClickListerner)
    }

    override fun getItemCount(): Int {
        return chamadasAbertas.size
    }

    class ChamadaAbertaViewHolder(itemView: View) : ViewHolder(itemView) {
        fun biding(chamadaAberta : Chamada, onClickListerner: Consumer<Long>){
            itemView.findViewById<TextView>(R.id.txNomeMateiraAberta).text = chamadaAberta.atividade.materia.descricao
            itemView.findViewById<ConstraintLayout>(R.id.constRow).setOnClickListener {
                onClickListerner.accept(chamadaAberta.id)
            }
        }
    }
}