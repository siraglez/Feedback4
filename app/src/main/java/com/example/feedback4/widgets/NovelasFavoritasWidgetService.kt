package com.example.feedback4.widgets

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.feedback4.R
import com.example.feedback4.baseDeDatos.NovelaDatabaseHelper

class NovelasFavoritasWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return NovelasFavoritasRemoteViewsFactory(applicationContext)
    }
}

class NovelasFavoritasRemoteViewsFactory(private val context: Context) : RemoteViewsService.RemoteViewsFactory {

    private var novelasFavoritas = listOf<String>()
    private lateinit var dbHelper: NovelaDatabaseHelper

    override fun onCreate() {
        dbHelper = NovelaDatabaseHelper(context)
    }

    override fun onDataSetChanged() {
        novelasFavoritas = dbHelper.obtenerNovelasFavoritas().map { it.titulo }
    }

    override fun onDestroy() {
        dbHelper.close()
    }

    override fun getCount(): Int = novelasFavoritas.size

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_item_novela)
        views.setTextViewText(R.id.widget_novela_titulo, novelasFavoritas[position])
        return views
    }

    override fun getLoadingView(): RemoteViews? = null
    override fun getViewTypeCount(): Int = 1
    override fun getItemId(position: Int): Long = position.toLong()
    override fun hasStableIds(): Boolean = true
}
