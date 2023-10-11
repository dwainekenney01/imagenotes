package com.dwainekenney.imagenotes.domain.models

data class NoteModel(
    val id: String = (0 until Int.MAX_VALUE).random().toString(), // TODO: Determine better unique ID generator
    val noteText: String = "",
    val noteImageUri: String? = null,
    val createdTimestamp: Long = System.currentTimeMillis(),
    val lastEditedTimestamp: Long = System.currentTimeMillis()
) {
    val firstLine = noteText.split("\n").first()
}