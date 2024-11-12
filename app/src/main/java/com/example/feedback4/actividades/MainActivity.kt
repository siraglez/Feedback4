package com.example.feedback4.actividades

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.feedback4.R
import com.example.feedback4.baseDeDatos.NovelaDatabaseHelper
import com.example.feedback4.fragments.AgregarNovelaFragment
import com.example.feedback4.fragments.DetallesNovelaFragment
import com.example.feedback4.fragments.ListaNovelasFragment
import com.example.feedback4.dataClasses.Novela
import com.example.feedback4.fragments.AgregarResenaFragment

class MainActivity : AppCompatActivity(), ListaNovelasFragment.OnNovelaSelectedListener {

    private lateinit var novelaDbHelper: NovelaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        novelaDbHelper = NovelaDatabaseHelper(this)

        // Cargar el fragmento de lista de novelas al inicio
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListaNovelasFragment())
                .commit()
        }

        // Configuración del botón de configuración para abrir ConfiguracionActivity
        val btnConfiguracion = findViewById<ImageButton>(R.id.btnConfiguracion)
        btnConfiguracion.setOnClickListener {
            val intent = Intent(this, ConfiguracionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNovelaSelected(novela: Novela) {
        val detallesFragment = DetallesNovelaFragment.newInstance(novela)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detallesFragment)
            .addToBackStack(null)
            .commit()
    }

    fun mostrarAgregarNovelaFragment() {
        val agregarFragment = AgregarNovelaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, agregarFragment)
            .addToBackStack(null)
            .commit()
    }

    fun mostrarAgregarResenaFragment(novela: Novela) {
        val agregarResenaFragment = AgregarResenaFragment.newInstance(novela.titulo)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, agregarResenaFragment)
            .addToBackStack(null)
            .commit()
    }
}
