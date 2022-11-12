package com.fatec.sul.appdoaluno.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.model.api.delivery.AcenoDelivery
import java.util.function.Consumer

class AcenoAdapter(private val acenos : List<AcenoDelivery>,private val listener : Consumer<Long>) : Adapter<AcenoAdapter.AcenoViewHolder>(){
    class AcenoViewHolder(itemView: View) : ViewHolder(itemView) {
        fun binding(aceno: AcenoDelivery, listener : Consumer<Long>){
            val descricaoMateria = itemView.findViewById<TextView>(R.id.txNomeMateira)
            val professor = itemView.findViewById<TextView>(R.id.txProfessor)
            val contador = itemView.findViewById<TextView>(R.id.txCountadorAcenos)
            val imgConfirmar = itemView.findViewById<ImageView>(R.id.imgConfirmAceno)
            val txDescricaoAceno = itemView.findViewById<TextView>(R.id.txDescricaoAceno)

            descricaoMateria.text = aceno.nomeMateria
            professor.text = "${aceno.nomeUsuario}:"
            txDescricaoAceno.text = aceno.descricao
            contador.text = aceno.confirmacoes.toString()
            imgConfirmar.setOnClickListener {
                listener.accept(aceno.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcenoViewHolder {
        return AcenoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_aceno,parent,false))
    }

    override fun onBindViewHolder(holder: AcenoViewHolder, position: Int) {
        holder.binding(acenos[position],listener)
    }

    override fun getItemCount(): Int {
        return acenos.size
    }
}