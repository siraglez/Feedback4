package com.example.feedback4.widgets

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.net.toUri
import com.example.feedback4.R
import com.example.feedback4.actividades.MainActivity

class NovelasFavoritasWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // Actualiza todos los widgets activos
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    companion object {
        private const val ACTION_VIEW_DETAILS = "com.example.app.ACTION_VIEW_DETAILS"

        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            // Define el layout del widget
            val views = RemoteViews(context.packageName, R.layout.widget_novelas_favoritas)

            // Configura el RemoteViewsService que llenará la lista de novelas favoritas
            val intent = Intent(context, NovelasFavoritasWidgetService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = this.toUri(Intent.URI_INTENT_SCHEME).toUri()
            }
            views.setRemoteAdapter(R.id.widget_list, intent)

            // Configura el PendingIntent para abrir la actividad de detalles al hacer clic en un ítem
            val clickIntent = Intent(context, MainActivity::class.java).apply {
                action = ACTION_VIEW_DETAILS
            }
            val clickPendingIntent = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(clickIntent)
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            }
            views.setPendingIntentTemplate(R.id.widget_list, clickPendingIntent)

            // Actualiza el widget con las vistas configuradas
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
