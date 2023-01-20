package com.contelli.jetnote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val note: String,
    @ColumnInfo(name = "created_at")
    val date: Date = Date()
)
