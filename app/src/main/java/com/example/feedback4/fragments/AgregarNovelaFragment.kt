package com.example.feedback4.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.feedback4.R
import com.example.feedback4.baseDeDatos.NovelaDatabaseHelper
import com.example.feedback4.dataClasses.Novela

class AgregarNovelaFragment : Fragment() {

    private lateinit var listener: OnNovelaAgregadaListener
    private lateinit var novelaDbHelper: NovelaDatabaseHelper

    // Interface para notificar a MainActivity cuando se agrega una novela
    interface OnNovelaAgregadaListener {
        fun onNovelaAgregada()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNovelaAgregadaListener) {
            listener = context
            novelaDbHelper = NovelaDatabaseHelper(context)
        } else {
            throw RuntimeException("$context must implement OnNovelaAgregadaListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_agregar_novela, container, false)

        val tituloEditText = view.findViewById<EditText>(R.id.etTitulo)
        val autorEditText = view.findViewById<EditText>(R.id.etAutor)
        val anioEditText = view.findViewById<EditText>(R.id.etAnio)
        val sinopsisEditText = view.findViewById<EditText>(R.id.etSinopsis)

        view.findViewById<Button>(R.id.btnAgregar).setOnClickListener {
            val titulo = tituloEditText.text.toString()
            val autor = autorEditText.text.toString()
            val anio = anioEditText.text.toString().toIntOrNull() ?: 0
            val sinopsis = sinopsisEditText.text.toString()

            if (titulo.isNotBlank() && autor.isNotBlank() && anio > 0) {
                val novela = Novela(titulo, autor, anio, sinopsis, esFavorita = false)
                novelaDbHelper.agregarNovela(novela)
                Toast.makeText(requireContext(), "Novela agregada", Toast.LENGTH_SHORT).show()
                listener.onNovelaAgregada()
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        novelaDbHelper.close() // Cerrar la base de datos al salir del fragmento
    }
}
