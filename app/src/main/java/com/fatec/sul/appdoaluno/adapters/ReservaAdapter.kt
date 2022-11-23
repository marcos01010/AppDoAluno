package com.fatec.sul.appdoaluno.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fatec.sul.appdoaluno.R
import com.fatec.sul.appdoaluno.model.api.Reserva
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

class ReservaAdapter(private val reservas: List<Reserva>) : Adapter<ReservaAdapter.ReservaViewHolder>() {
    class ReservaViewHolder(itemView: View) : ViewHolder(itemView) {
        private val solicitante: TextView = itemView.findViewById(R.id.txSolicitanteReserva)
        private val sala: TextView = itemView.findViewById(R.id.txSalaReserva)
        private val tempo: TextView = itemView.findViewById(R.id.txPeriodoReserva)

        fun biding(reserva: Reserva){
            val df = SimpleDateFormat("dd MMMM yyyy hh:mm")
            val inicio = Date(Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(reserva.inicio))
                .toEpochMilli())
            val fim = Date(Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(reserva.fim))
                .toEpochMilli())

            solicitante.text = reserva.nomeSolicitante
            sala.text = "${reserva.descricao} - ${reserva.numeroSala}"
            tempo.text = df.format(inicio) + " à " + df.format(fim)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        return ReservaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_reserva,parent,false))
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        holder.biding(reservas[position])
    }

    override fun getItemCount(): Int {
        return reservas.size
    }
}