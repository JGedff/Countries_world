package com.example.ac02paisesdelmundo

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    /*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_actinobar, menu)
            return true
        }

        // gestionamos el menú
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // creamos un nuevo alumno aleatorio
            val nombre: String = randomUtils.randomString(20)
            val apellido: String = randomUtils.randomString(30)
            val edad: Int = randomUtils.randomInteger(16,35)

            when (item.itemId) {
                R.id.miNew -> {
                    mlAlumno.add(cAlumno(nombre, apellido, edad))
                    mAdapter.update(mlAlumno)
                }
                R.id.init -> {
                    mlAlumno.add(0, cAlumno(nombre, apellido, edad))
                    mAdapter.update(mlAlumno)
                }
                R.id.mid -> {
                    val size: Int = mlAlumno.count()
                    val index: Int = size / 2

                    mlAlumno.add(index, cAlumno(nombre, apellido, edad))
                    mAdapter.update(mlAlumno)
                }
            }
            return(super.onOptionsItemSelected(item));
        }*/

    fun getPaises(): MutableList<Pais> {
        val paises:MutableList<Pais>
        val jsonInfo: Paises

        val json: String = this.assets.open("paisos.json").bufferedReader().use { it.readText() }
        val gson: Gson = Gson()

        jsonInfo = gson.fromJson(json, Paises::class.java)
        paises = jsonInfo.countries

        return paises
    }
}