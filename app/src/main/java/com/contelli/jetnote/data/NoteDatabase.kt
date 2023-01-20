package com.contelli.jetnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.contelli.jetnote.model.Note
import com.contelli.jetnote.util.DateConverter
import com.contelli.jetnote.util.UuidConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UuidConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}