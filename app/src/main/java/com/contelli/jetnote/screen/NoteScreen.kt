package com.contelli.jetnote.screen

//import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.contelli.jetnote.R
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(viewModel: NoteViewModel = viewModel()) {
    val noteList = viewModel.noteList.collectAsState().value
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
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.padding(8.dp))
            if (noteList.isNotEmpty()) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(noteList) {
                        Card(
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize()
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    val sdf = SimpleDateFormat("EEEE, d MMMM")
                                    Text(
                                        text = it.title,
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Text(text = it.note, style = MaterialTheme.typography.bodySmall)
                                    Text(
                                        text = sdf.format(it.date),
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }
                                IconButton({
                                    viewModel.removeNote(it)
                                }) {
                                    Icon(
                                        Icons.Filled.Delete,
                                        "Delete Button",
                                        tint = MaterialTheme.colorScheme.error
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
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF919191)
                    )
                }
            }
        }
    }
}