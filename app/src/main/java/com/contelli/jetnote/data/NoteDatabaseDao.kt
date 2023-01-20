package com.contelli.jetnote.data

import androidx.room.*
import com.contelli.jetnote.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note: Note)
}
