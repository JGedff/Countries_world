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

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView : RecyclerView
    lateinit var mAdapter : countryAdapter
    lateinit var mlPais: MutableList<Pais>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = ""

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        mAdapter = countryAdapter()
        mlPais = getPaises()

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
        return(super.onOptionsItemSelected(item));
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