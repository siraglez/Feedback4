package com.example.feedback4.actividades

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feedback4.R
import com.example.feedback4.baseDeDatos.NovelaDatabaseHelper
import com.example.feedback4.fragments.AgregarNovelaFragment
import com.example.feedback4.fragments.ListaNovelasFragment
import com.example.feedback4.fragments.DetallesNovelaFragment
import com.example.feedback4.fragments.AgregarResenaFragment
import com.example.feedback4.dataClasses.Novela

class MainActivity : AppCompatActivity(), ListaNovelasFragment.OnNovelaSelectedListener, AgregarNovelaFragment.OnNovelaAgregadaListener {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var novelaDbHelper: NovelaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("UsuarioPreferences", MODE_PRIVATE)
        aplicarTema()

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

    fun mostrarAgregarNovelaFragment() {
        val agregarFragment = AgregarNovelaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, agregarFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onNovelaSelected(novela: Novela) {
        val detallesFragment = DetallesNovelaFragment.newInstance(novela)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detallesFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onNovelaAgregada() {
        // Regresa a la lista de novelas y actualiza
        supportFragmentManager.popBackStack()
        val listaFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? ListaNovelasFragment
        listaFragment?.actualizarListaNovelas()
    }

    private fun aplicarTema() {
        val temaOscuro = sharedPreferences.getBoolean("temaOscuro", false)
        setTheme(if (temaOscuro) R.style.Theme_Feedback4_Night else R.style.Theme_Feedback4_Day)
    }

    override fun onDestroy() {
        super.onDestroy()
        novelaDbHelper.close()
    }

    fun mostrarAgregarResenaFragment(novela: Novela) {
        val agregarResenaFragment = AgregarResenaFragment.newInstance(novela.titulo)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, agregarResenaFragment)
            .addToBackStack(null)
            .commit()
    }
}
