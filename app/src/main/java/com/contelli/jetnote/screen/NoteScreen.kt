package com.contelli.jetnote.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.contelli.jetnote.R
import java.text.SimpleDateFormat

@Composable
fun NoteScreen(viewModel: NoteViewModel = viewModel()) {
    val noteList = viewModel.getAllNotes()
    Column {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        })
        Column(Modifier.padding(16.dp)) {
            TextField(value = viewModel.getTitleInput(),
                onValueChange = { viewModel.setTitleInput(it) },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Title")
                })
            Spacer(modifier = Modifier.padding(4.dp))
            TextField(value = viewModel.getNoteInput(),
                onValueChange = { viewModel.setNoteInput(it) },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Note")
                })
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = {
                viewModel.addNote()
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Save", style = MaterialTheme.typography.button)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            if (noteList.isNotEmpty()) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(noteList) {
                        Surface(
                            color = MaterialTheme.colors.primary.copy(alpha = 0.125f),
                            shape = MaterialTheme.shapes.small,
                            elevation = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    val sdf = SimpleDateFormat("EEEE, d MMMM")
                                    Text(text = it.title, style = MaterialTheme.typography.h6)
                                    Text(text = it.note, style = MaterialTheme.typography.subtitle1)
                                    Text(
                                        text = sdf.format(it.date),
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                                IconButton(onClick = {
                                    viewModel.removeNote(it)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Trash Icon",
                                        tint = MaterialTheme.colors.error
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "List Icon",
                        modifier = Modifier
                            .width(80.dp)
                            .aspectRatio(1f),
                        tint = Color(0xFF919191)
                    )
                    Text(
                        text = "No notes yet",
                        style = MaterialTheme.typography.h6,
                        color = Color(0xFF919191)
                    )
                }
            }
        }
    }
}