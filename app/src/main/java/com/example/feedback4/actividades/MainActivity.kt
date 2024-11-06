package com.example.feedback4.actividades

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feedback4.R
import com.example.feedback4.baseDeDatos.NovelaDatabaseHelper
import com.example.feedback4.dataClasses.Novela
import com.example.feedback4.fragments.AgregarNovelaFragment
import com.example.feedback4.fragments.AgregarResenaFragment
import com.example.feedback4.fragments.DetallesNovelaFragment
import com.example.feedback4.fragments.ListaNovelasFragment

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
    }

    override fun onNovelaSelected(novela: Novela) {
        // Mostrar el fragmento de detalles al seleccionar una novela
        val detallesFragment = DetallesNovelaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detallesFragment)
            .addToBackStack(null)
            .commit()

        // Pasar los detalles de la novela seleccionada al fragmento de detalles
        detallesFragment.mostrarDetalles(novela)
    }

    fun mostrarAgregarNovelaFragment() {
        val agregarFragment = AgregarNovelaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, agregarFragment)
            .addToBackStack(null)
            .commit()
    }

    fun mostrarAgregarResenaFragment(novela: Novela) {
        val agregarResenaFragment = AgregarResenaFragment().apply {
            arguments = Bundle().apply {
                putString("tituloNovela", novela.titulo)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, agregarResenaFragment)
            .addToBackStack(null)
            .commit()
    }
}
