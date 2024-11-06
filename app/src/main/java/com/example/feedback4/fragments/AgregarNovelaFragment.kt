package com.example.feedback4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.feedback4.R

class AgregarNovelaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_agregar_novela, container, false)

        // Implementación para agregar una novela al pulsar el botón
        view.findViewById<Button>(R.id.btnAgregar).setOnClickListener {
            // Lógica para agregar novela a la base de datos
        }

        return view
    }
}
