package com.example.feedback4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.feedback4.R
import com.example.feedback4.dataClasses.Novela

class DetallesNovelaFragment : Fragment() {

    private lateinit var textViewTitulo: TextView
    private lateinit var textViewAutor: TextView
    private lateinit var textViewAnio: TextView
    private lateinit var textViewSinopsis: TextView
    private lateinit var listViewResenas: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalles_novela, container, false)
        textViewTitulo = view.findViewById(R.id.tvTitulo)
        textViewAutor = view.findViewById(R.id.tvAutor)
        textViewAnio = view.findViewById(R.id.tvAnio)
        textViewSinopsis = view.findViewById(R.id.tvSinopsis)
        listViewResenas = view.findViewById(R.id.listViewResenas)
        return view
    }

    fun mostrarDetalles(novela: Novela) {
        textViewTitulo.text = "Título: ${novela.titulo}"
        textViewAutor.text = "Autor: ${novela.autor}"
        textViewAnio.text = "Año: ${novela.anioPublicacion}"
        textViewSinopsis.text = "Sinopsis: ${novela.sinopsis}"
        // Puedes agregar el adaptador para mostrar las reseñas
    }
}
