package com.contelli.jetnote.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.contelli.jetnote.R
import com.contelli.jetnote.model.Note
import java.util.*

@Composable
fun NoteScreen() {
    val titleInput = remember {
        mutableStateOf("")
    }
    val noteInput = remember {
        mutableStateOf("")
    }
    val noteList = remember {
        mutableStateOf(listOf<Note>(Note(id = UUID.randomUUID(), title = "Title", note = "Note")))
    }
    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {})
        Spacer(modifier = Modifier.padding(4.dp))
        OutlinedTextField(value = titleInput.value, onValueChange = {
            titleInput.value = it
        }, modifier = Modifier.fillMaxWidth(), singleLine = true, label = {
            Text(text = "Title")
        }, enabled = true)
        Spacer(modifier = Modifier.padding(4.dp))
        OutlinedTextField(value = noteInput.value, onValueChange = {
            noteInput.value = it
        }, modifier = Modifier.fillMaxWidth(), singleLine = true, label = {
            Text(text = "Note")
        })
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save", style = MaterialTheme.typography.button)
        }
        LazyColumn {
            items(noteList.value) {
                Card(elevation = 3.dp) {
                    Column() {
                        Text(text = it.title)
                        Text(text = it.note)
                        Text(text = "Thu, 28, Oct")
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NotePreview() {
    NoteScreen()
}