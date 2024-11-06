package com.example.feedback4.widgets

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.feedback4.R
import com.example.feedback4.baseDeDatos.NovelaDatabaseHelper
import com.example.feedback4.dataClasses.Novela

class NovelasFavoritasWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return NovelasFavoritasRemoteViewsFactory(applicationContext)
    }
}

class NovelasFavoritasRemoteViewsFactory(private val context: Context) : RemoteViewsService.RemoteViewsFactory {

    private var novelasFavoritas: List<Novela> = emptyList()

    override fun onCreate() {
        // Carga las novelas favoritas desde la base de datos
        val dbHelper = NovelaDatabaseHelper(context)
        novelasFavoritas = dbHelper.obtenerNovelasFavoritas()
    }

    override fun onDataSetChanged() {
        // Recarga los datos cuando el widget necesite actualizarse
        val dbHelper = NovelaDatabaseHelper(context)
        novelasFavoritas = dbHelper.obtenerNovelasFavoritas()
    }

    override fun onDestroy() {
        // Limpieza de recursos, si es necesario
    }

    override fun getCount(): Int = novelasFavoritas.size

    override fun getViewAt(position: Int): RemoteViews {
        val novela = novelasFavoritas[position]
        val views = RemoteViews(context.packageName, R.layout.widget_item_novela_favorita)

        views.setTextViewText(R.id.widget_novela_titulo, novela.titulo)

        // Configura el Intent para abrir los detalles de la novela
        val fillInIntent = Intent().apply {
            putExtra("NOVELA_TITULO", novela.titulo)
        }
        views.setOnClickFillInIntent(R.id.widget_novela_titulo, fillInIntent)

        return views
    }

    override fun getLoadingView(): RemoteViews? = null
    override fun getViewTypeCount(): Int = 1
    override fun getItemId(position: Int): Long = position.toLong()
    override fun hasStableIds(): Boolean = true
}
