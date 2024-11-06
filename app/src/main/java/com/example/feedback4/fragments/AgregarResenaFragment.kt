package com.example.feedback4.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.feedback4.R

class AgregarResenaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_agregar_resena, container, false)

        val tituloNovela = arguments?.getString("tituloNovela")

        view.findViewById<Button>(R.id.btnAgregarResena).setOnClickListener {
            val resena = view.findViewById<EditText>(R.id.etResena).text.toString()
            // Lógica para guardar la reseña en la base de datos usando el título de la novela
        }

        return view
    }

}
