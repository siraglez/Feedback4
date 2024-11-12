package com.example.feedback4.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.feedback4.adapters.NovelaAdapter
import com.example.feedback4.R
import com.example.feedback4.actividades.MainActivity
import com.example.feedback4.baseDeDatos.NovelaDatabaseHelper
import com.example.feedback4.dataClasses.Novela

class ListaNovelasFragment : Fragment() {

    private lateinit var listener: OnNovelaSelectedListener
    private lateinit var novelaDbHelper: NovelaDatabaseHelper
    private lateinit var adapter: NovelaAdapter

    interface OnNovelaSelectedListener {
        fun onNovelaSelected(novela: Novela)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNovelaSelectedListener) {
            listener = context
            novelaDbHelper = NovelaDatabaseHelper(context)
        } else {
            throw RuntimeException("$context debe implementar OnNovelaSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_novelas, container, false)
        val listViewNovelas = view.findViewById<ListView>(R.id.listViewNovelas)
        val btnAgregarNovela = view.findViewById<Button>(R.id.btnAgregarNovela)

        // Obtener y mostrar novelas
        val novelas = novelaDbHelper.obtenerNovelas()
        adapter = NovelaAdapter(requireContext(), novelas)
        listViewNovelas.adapter = adapter

        listViewNovelas.setOnItemClickListener { _, _, position, _ ->
            val novela = adapter.getItem(position)
            novela?.let { listener.onNovelaSelected(it) }
        }

        btnAgregarNovela.setOnClickListener {
            (activity as? MainActivity)?.mostrarAgregarNovelaFragment()
        }

        return view
    }

    fun actualizarListaNovelas() {
        val novelas = novelaDbHelper.obtenerNovelas()
        adapter.clear()
        adapter.addAll(novelas)
        adapter.notifyDataSetChanged()
    }
}
