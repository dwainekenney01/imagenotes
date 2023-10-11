package com.dwainekenney.imagenotes.ui.navigation

object Routes {

    // Routes
    private const val NOTES_ROUTE = "notes"
    private const val NOTE_ROUTE = "note"
    private const val SETTINGS_ROUTE = "settings"

    // Arguments
    const val ID_ARG_KEY = "id"

    fun notesRoute() = NOTES_ROUTE

    // Setting id to null will create default nav route
    fun noteRoute(id: String? = null) = "$NOTE_ROUTE/${formatArgument(ID_ARG_KEY, id)}"

    fun settingsRoute() = SETTINGS_ROUTE

    private fun formatArgument(key: String, argument: String?) = argument ?: "{$key}"
}