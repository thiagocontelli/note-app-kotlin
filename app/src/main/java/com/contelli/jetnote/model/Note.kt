package com.contelli.jetnote.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Note(
    val id: UUID,
    val title: String,
    val note: String,
    val date: Date
)
