package com.dwainekenney.imagenotes.domain.models

data class NoteModel(
    val noteText: String,
    val noteImageUri: String?,
    val createdTimestamp: Long,
    val lastEditedTimestamp: Long
)