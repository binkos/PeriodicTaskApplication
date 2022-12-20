package com.uladzislau.pravalenak.periodictaskapplication.add

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uladzislau.pravalenak.periodictaskapplication.extension.currentOrThrow
import com.uladzislau.pravalenak.periodictaskapplication.navigation.LocalNavigator

@Composable
fun AddNote() {
    val viewModel: AddNoteViewModel = viewModel()
    val state by viewModel.stateFlow.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    AddNoteUI(state = state, onAction = viewModel::handleEvent)

    LaunchedEffect(key1 = Unit) {
        viewModel.events.collect {
            navigator.popBackStack()
        }
    }
}

@Composable
private fun AddNoteUI(
    state: AddNoteState,
    onAction: (AddNoteEvent) -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp),
            text = "Title"
        )
        TextField(
            modifier = Modifier
                .padding(top = 4.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = state.title,
            onValueChange = {
                onAction(AddNoteEvent.OnTitleUpdate(it))
            }
        )

        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp),
            text = "Description"
        )
        TextField(
            modifier = Modifier
                .padding(top = 4.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = state.description,
            onValueChange = {
                onAction(AddNoteEvent.OnDescriptionUpdate(it))
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            onClick = {
                onAction(AddNoteEvent.OnNoteAdded(state.title, state.description))
            }
        ) {
            Text(text = "Add Note")
        }
    }
}