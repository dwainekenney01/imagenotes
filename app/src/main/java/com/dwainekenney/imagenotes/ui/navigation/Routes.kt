package com.dwainekenney.imagenotes.ui.navigation

object Routes {

    // Routes
    private const val NOTES_ROUTE = "notes"
    private const val NOTE_ROUTE = "note/%s"
    private const val SETTINGS_ROUTE = "settings"

    // Arguments
    const val ID_ARG_KEY = "id"

    fun notesRoute() = NOTES_ROUTE
    fun noteRoute(id: String? = null) = String.format(NOTE_ROUTE, formatArgument(NOTE_ROUTE, id))
    fun settingsRoute() = SETTINGS_ROUTE

    private fun formatArgument(key: String, argument: String?) = argument ?: "{$key}"
}