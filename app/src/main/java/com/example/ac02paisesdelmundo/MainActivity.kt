package com.example.ac02paisesdelmundo

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ac02paisesdelmundo.adapters.countryAdapter
import com.example.ac02paisesdelmundo.classes.Pais
import com.example.ac02paisesdelmundo.classes.Paises
import com.google.gson.Gson
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView : RecyclerView
    lateinit var mAdapter : countryAdapter
    lateinit var mlPais: MutableList<Pais>
    lateinit var filtredPaises: MutableList<Pais>
    var OrderBy = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = ""

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        mAdapter = countryAdapter()
        mlPais = getPaises()
        filtredPaises = mlPais

        setUpRecyclerView()
    }

    fun setUpRecyclerView(){
        myRecyclerView = findViewById(R.id.rvPaisos) as RecyclerView
        myRecyclerView.setHasFixedSize(true)
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter.countryAdapter(mlPais, this)

        myRecyclerView.adapter = mAdapter
    }

    // creamos el menú.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // gestionamos el menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.asc_capital -> orderCapital(true)
            R.id.desc_capital -> orderCapital(false)
            R.id.asc_country -> orderCountry(true)
            R.id.desc_country -> orderCountry(false)
            R.id.no_filter_continent -> doNotFilterContinent()
            R.id.filter_africa -> filterContinent("Africa")
            R.id.filter_antarctica -> filterContinent("Antarctica")
            R.id.filter_europe -> filterContinent("Europe")
            R.id.filter_oceania -> filterContinent("Oceania")
            R.id.filter_n_america -> filterContinent("North America")
            R.id.filter_s_america -> filterContinent("South America")
            R.id.no_favorite -> doNotFilterFavorite()
            R.id.filter_favorite -> filterFavorite(true)
            R.id.filter_no_favorite -> filterFavorite(false)
        }

        return(super.onOptionsItemSelected(item));
    }

    fun orderCapital(asc: Boolean) {
        if (asc) {
            if (Locale.getDefault().language == "es") {
                filtredPaises.sortBy { it.capitalEs }
            } else {
                filtredPaises.sortBy { it.capitalEn }
            }
        } else {
            if (Locale.getDefault().language == "es") {
                filtredPaises.sortByDescending { it.capitalEs }
            } else {
                filtredPaises.sortByDescending { it.capitalEn }
            }
        }

        mAdapter.update(filtredPaises)
    }

    fun filterContinent(continent: String) {
        val newPaises: MutableList<Pais> = arrayListOf()

        mlPais.forEach({
            if (it.continentEn == continent) {
                newPaises.add(it)
            }
        })

        mAdapter.update(newPaises)
        filtredPaises = newPaises
    }

    fun orderCountry(asc: Boolean) {
        if (asc) {
            if (Locale.getDefault().language == "es") {
                filtredPaises.sortBy { it.nameEs }
            } else {
                filtredPaises.sortBy { it.nameEn }
            }
        } else {
            if (Locale.getDefault().language == "es") {
                filtredPaises.sortByDescending { it.nameEs }
            } else {
                filtredPaises.sortByDescending { it.nameEn }
            }
        }

        mAdapter.update(filtredPaises)
    }

    //Al desactivar un filtre, es desactiven els dos.
    fun doNotFilterContinent() {
        mAdapter.update(mlPais)
        filtredPaises = mlPais
    }

    fun doNotFilterFavorite() {
        mAdapter.update(mlPais)
        filtredPaises = mlPais
    }

    fun filterFavorite(filter: Boolean) {
        val newPaises: MutableList<Pais> = arrayListOf()

        if (filter) {
            mlPais.forEach({
                if (it.favorite) {
                    newPaises.add(it)
                }
            })
        } else {
            mlPais.forEach({
                if (!it.favorite) {
                    newPaises.add(it)
                }
            })
        }

        mAdapter.update(newPaises)
        filtredPaises = newPaises
    }

    fun getPaises(): MutableList<Pais> {
        val paises:MutableList<Pais>
        val jsonInfo: Paises

        val json: String = this.assets.open("paisos.json").bufferedReader().use { it.readText() }
        val gson: Gson = Gson()

        jsonInfo = gson.fromJson(json, Paises::class.java)
        paises = jsonInfo.countries

        return paises
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mAdapter.onActivityResult(requestCode, resultCode, data!!)
    }

}