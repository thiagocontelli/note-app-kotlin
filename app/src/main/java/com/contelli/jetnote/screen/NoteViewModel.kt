package com.contelli.jetnote.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.contelli.jetnote.model.Note
import java.util.*

class NoteViewModel : ViewModel() {
    private val noteList = mutableStateListOf<Note>()
    private val titleInput = mutableStateOf("")
    private val noteInput = mutableStateOf("")

    fun addNote() {
        val titleExists = noteList.filter { it.title == titleInput.value }.isNotEmpty()
        val noteExists = noteList.filter { it.note == noteInput.value }.isNotEmpty()
        if (titleInput.value.trim().isEmpty() || noteInput.value.trim().isEmpty()) return
        if (titleExists && noteExists) return
        noteList.add(
            Note(
                id = UUID.randomUUID(),
                title = titleInput.value,
                note = noteInput.value,
                date = Date()
            )
        )
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes(): MutableList<Note> {
        return noteList
    }

    fun setTitleInput(title: String) {
        titleInput.value = title
    }

    fun getTitleInput(): String {
        return titleInput.value
    }

    fun setNoteInput(note: String) {
        noteInput.value = note
    }

    fun getNoteInput(): String {
        return noteInput.value
    }
}