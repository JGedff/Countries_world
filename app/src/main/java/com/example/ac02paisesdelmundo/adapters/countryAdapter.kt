package com.example.ac02paisesdelmundo.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ac02paisesdelmundo.R
import com.example.ac02paisesdelmundo.classes.Pais
import com.example.ac02paisesdelmundo.classes.Paises
import java.lang.reflect.Type
import java.util.Locale

class countryAdapter: RecyclerView.Adapter<countryAdapter.ViewHolder>() {
    lateinit var paisos: MutableList<Pais>
    lateinit var context: Context

    fun countryAdapter(paisos: MutableList<Pais>, context: Context) {
        this.paisos = paisos
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = paisos.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.recycler_card, parent, false))
    }

    override fun getItemCount(): Int {
        return paisos.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.tvName)
        val continent = view.findViewById<TextView>(R.id.tvContinent)
        val capital = view.findViewById<TextView>(R.id.tvCapital)
        val phoneCode = view.findViewById<TextView>(R.id.tvPhoneCode)
        val code2 = view.findViewById<TextView>(R.id.tvCode2)
        val code3 = view.findViewById<TextView>(R.id.tvCode3)
        val webDomain = view.findViewById<TextView>(R.id.tvTld)
        val km = view.findViewById<TextView>(R.id.tvKm2)
        val emoji = view.findViewById<TextView>(R.id.tvEmoji)

        val card = view.findViewById<CardView>(R.id.card)

        val imvFavorite = view.findViewById<ImageView>(R.id.imvFavorite)

        fun bind(pais: Pais, context: Context) {
            if (Locale.getDefault().language == "en") {
                name.text = pais.nameEn
                continent.text = pais.continentEn
                capital.text = pais.capitalEn
            } else {
                name.text = pais.nameEs
                continent.text = pais.continentEs
                capital.text = pais.capitalEs
            }

            phoneCode.text = pais.dialCode
            code2.text = pais.code2
            code3.text = pais.code3
            webDomain.text = pais.tld
            km.text = pais.km2.toString()
            emoji.text = pais.emoji

            if (pais.km2 > 1000000) {
                km.setTypeface(null, Typeface.BOLD);
            }

            if (pais.favorite) {
                imvFavorite.setImageResource(android.R.drawable.btn_star_big_on)
            }
            else {
                imvFavorite.setImageResource(android.R.drawable.btn_star_big_off)
            }

            imvFavorite.setOnClickListener(View.OnClickListener {
                pais.favorite = !pais.favorite

                if (pais.favorite) {
                    imvFavorite.setImageResource(android.R.drawable.btn_star_big_on)
                }
                else {
                    imvFavorite.setImageResource(android.R.drawable.btn_star_big_off)
                }
            })

            if (pais.continentEn == "Africa") {
                card.setCardBackgroundColor(Color.parseColor("#f5bc42"))
            } else if (pais.continentEn == "Europe") {
                card.setCardBackgroundColor(Color.parseColor("#ff6161"))
            } else if (pais.continentEn == "Oceania") {
                card.setCardBackgroundColor(Color.parseColor("#61c8ff"))
            } else if (pais.continentEn == "North America") {
                card.setCardBackgroundColor(Color.parseColor("#61ffa0"))
            } else if (pais.continentEn == "Antarctica") {
                card.setCardBackgroundColor(Color.parseColor("#c061ff"))
            } else if (pais.continentEn == "South America") {
                card.setCardBackgroundColor(Color.parseColor("#ff61e5"))
            }
        }
    }

    fun update(datos: MutableList<Pais>){
        this.paisos = datos
        this.notifyDataSetChanged()
    }
}