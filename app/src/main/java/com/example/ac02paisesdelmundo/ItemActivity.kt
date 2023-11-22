package com.example.ac02paisesdelmundo

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import java.util.Locale

class ItemActivity : AppCompatActivity() {
    lateinit var capitalEn: String
    lateinit var capitalEs: String
    lateinit var code2: String
    lateinit var code3: String
    lateinit var continentEn: String
    lateinit var continentEs: String
    lateinit var dialCode: String
    lateinit var emoji: String
    lateinit var nameEn: String
    lateinit var nameEs: String
    lateinit var tld: String

    var km2: Double = 0.0
    var favorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_card)

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        setGlobalValues()
        setValuesToView()
    }

    fun setGlobalValues() {
        capitalEn = intent.extras?.getString("capitalEn").toString()
        capitalEs = intent.extras?.getString("capitalEs").toString()
        code2 = intent.extras?.getString("code2").toString()
        code3 = intent.extras?.getString("code3").toString()
        continentEn = intent.extras?.getString("continentEn").toString()
        continentEs = intent.extras?.getString("continentEs").toString()
        dialCode = intent.extras?.getString("dialCode").toString()
        emoji = intent.extras?.getString("emoji").toString()
        favorite = intent.extras?.getBoolean("favourite")!!
        km2 = intent.extras?.getDouble("km2")!!
        nameEn = intent.extras?.getString("nameEn").toString()
        nameEs = intent.extras?.getString("nameEs").toString()
        tld = intent.extras?.getString("tld").toString()
    }

    fun setValuesToView() {
        val tv_name = findViewById<TextView>(R.id.tvName)
        val tv_continent = findViewById<TextView>(R.id.tvContinent)
        val tv_capital = findViewById<TextView>(R.id.tvCapital)
        val tv_phoneCode = findViewById<TextView>(R.id.tvPhoneCode)
        val tv_code2 = findViewById<TextView>(R.id.tvCode2)
        val tv_code3 = findViewById<TextView>(R.id.tvCode3)
        val tv_tld = findViewById<TextView>(R.id.tvTld)
        val tv_km2 = findViewById<TextView>(R.id.tvKm2)
        val tv_emoji = findViewById<TextView>(R.id.tvEmoji)
        val imvFavorite = findViewById<ImageView>(R.id.imvFavorite)
        val card = findViewById<CardView>(R.id.card)

        if (Locale.getDefault().language == "es") {
            tv_name.text = nameEs
            tv_continent.text = continentEs
            tv_capital.text = capitalEs
        } else {
            tv_name.text = nameEn
            tv_continent.text = continentEn
            tv_capital.text = capitalEn
        }

        tv_phoneCode.text = dialCode
        tv_emoji.text = emoji
        tv_code2.text = code2
        tv_code3.text = code3
        tv_tld.text = tld
        tv_km2.text = km2.toString()

        setKmBold(tv_km2)
        setBackgroundView(card)
        setFavoriteLogic(imvFavorite)
    }

    fun setKmBold(tv_km2: TextView) {
        if (km2 > 1000000) {
            tv_km2.setTypeface(null, Typeface.BOLD);
        } else {
            tv_km2.setTypeface(null, Typeface.NORMAL);
        }
    }

    fun setBackgroundView(card: CardView) {
        if (continentEn == "Africa") {
            card.setCardBackgroundColor(Color.parseColor("#f5bc42"))
        } else if (continentEn == "Europe") {
            card.setCardBackgroundColor(Color.parseColor("#ff6161"))
        } else if (continentEn == "Oceania") {
            card.setCardBackgroundColor(Color.parseColor("#61c8ff"))
        } else if (continentEn == "North America") {
            card.setCardBackgroundColor(Color.parseColor("#61ffa0"))
        } else if (continentEn == "Antarctica") {
            card.setCardBackgroundColor(Color.parseColor("#c061ff"))
        } else if (continentEn == "South America") {
            card.setCardBackgroundColor(Color.parseColor("#ff61e5"))
        }
    }

    fun setFavoriteLogic(imvFavorite: ImageView) {
        if (favorite) {
            imvFavorite.setImageResource(android.R.drawable.btn_star_big_on)
        }
        else {
            imvFavorite.setImageResource(android.R.drawable.btn_star_big_off)
        }

        imvFavorite.setOnClickListener(View.OnClickListener {
            favorite = !favorite

            if (favorite) {
                imvFavorite.setImageResource(android.R.drawable.btn_star_big_on)
            }
            else {
                imvFavorite.setImageResource(android.R.drawable.btn_star_big_off)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.item_menu, menu)
        return true
    }

    // gestionamos el menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.go_back -> endIntent()
        }

        return (super.onOptionsItemSelected(item))
    }

    fun endIntent() {
        var intent: Intent = Intent()

        intent.putExtra("favorite", favorite)
        intent.putExtra("name", nameEn)

        setResult(RESULT_OK, intent)
        finish()
    }
}