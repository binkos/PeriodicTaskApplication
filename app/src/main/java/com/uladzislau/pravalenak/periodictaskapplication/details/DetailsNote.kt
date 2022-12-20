package com.uladzislau.pravalenak.periodictaskapplication.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uladzislau.pravalenak.periodictaskapplication.models.NoteModel

@Composable
fun DetailsNote(noteId: Int) {
    val viewModel: DetailsViewModel = viewModel()
    val state by viewModel.stateFlow.collectAsState()

    DetailsNoteUI(state.note)

    LaunchedEffect(key1 = Unit) {
        viewModel.onInit(noteId)
    }
}

@Composable
private fun DetailsNoteUI(note: NoteModel) {
    Column(Modifier.padding(4.dp)) {
        Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Title")
        Text(modifier = Modifier.padding(horizontal = 8.dp), text = note.title)
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier.padding(horizontal = 8.dp), text = "description")
        Text(text = note.text)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.End), text = note.date
        )
    }
}


const val NOTE_ID = "NOTE_ID"