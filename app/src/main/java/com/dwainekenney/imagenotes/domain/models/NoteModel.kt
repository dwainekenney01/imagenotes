package com.dwainekenney.imagenotes.domain.models

import java.text.DateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date

data class NoteModel(
    val id: String = (0 until Int.MAX_VALUE).random().toString(), // TODO: Determine better unique ID generator
    val noteText: String = "",
    val noteImageUri: String? = null,
    val createdTimestamp: Long = System.currentTimeMillis(),
    val lastEditedTimestamp: Long = System.currentTimeMillis()
) {
    val firstLine get() = noteText.split("\n").first()
    val lastEditedString: String get() =
        Instant
            .ofEpochMilli(lastEditedTimestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .let { lastEdited ->
                val now = LocalDateTime.now()
                when {
                    now.isSameDate(lastEdited) -> {
                        lastEdited
                            .toLocalTime()
                            .format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
                    }
                    else -> {
                        lastEdited
                            .toLocalDate()
                            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    }
                }
            }

    private fun LocalDateTime.isSameDate(other: LocalDateTime) =
        this.dayOfMonth == other.dayOfMonth &&
                this.month == other.month &&
                this.year == other.year
}