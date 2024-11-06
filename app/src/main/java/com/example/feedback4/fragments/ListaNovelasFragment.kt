package com.example.feedback4.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.feedback4.NovelaAdapter
import com.example.feedback4.R
import com.example.feedback4.dataClasses.Novela

class ListaNovelasFragment : Fragment() {

    private lateinit var listViewNovelas: ListView
    private lateinit var adapter: NovelaAdapter
    private lateinit var listener: OnNovelaSelectedListener

    interface OnNovelaSelectedListener {
        fun onNovelaSelected(novela: Novela)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNovelaSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnNovelaSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_novelas, container, false)
        listViewNovelas = view.findViewById(R.id.listViewNovelas)
        adapter = NovelaAdapter(requireContext(), mutableListOf())
        listViewNovelas.adapter = adapter

        listViewNovelas.setOnItemClickListener { _, _, position, _ ->
            val novela = adapter.getItem(position) as Novela
            listener.onNovelaSelected(novela)
        }

        return view
    }

    fun mostrarNovelas(novelas: List<Novela>) {
        adapter.clear()
        adapter.addAll(novelas)
        adapter.notifyDataSetChanged()
    }
}
