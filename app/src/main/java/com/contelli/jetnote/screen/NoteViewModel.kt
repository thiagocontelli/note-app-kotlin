package com.contelli.jetnote.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.contelli.jetnote.model.Note
import com.contelli.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    private val titleInput = mutableStateOf("")
    private val noteInput = mutableStateOf("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect {
                if (it.isNullOrEmpty()) {
                    Log.d("Empty List", ": Empty List")
                }
                _noteList.value = it
            }
        }
    }

    fun addNote() = viewModelScope.launch {
        repository.addNote(
            Note(
                title = titleInput.value, note = noteInput.value
            )
        )
    }

    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

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