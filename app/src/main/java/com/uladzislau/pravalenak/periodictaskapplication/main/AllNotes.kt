package com.uladzislau.pravalenak.periodictaskapplication.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavOptionsBuilder
import com.uladzislau.pravalenak.periodictaskapplication.extension.currentOrThrow
import com.uladzislau.pravalenak.periodictaskapplication.models.NoteModel
import com.uladzislau.pravalenak.periodictaskapplication.navigation.LocalNavigator
import com.uladzislau.pravalenak.periodictaskapplication.navigation.Screen


@Composable
fun AllNotes() {
    val notesViewModel = viewModel<AllNotesViewModel>()
    val state by notesViewModel.stateFlow.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    AllNotesUI(
        state = state,
        onNavigation = navigator::navigate,
    )
}

@Composable
private fun AllNotesUI(
    state: AllNotesState,
    onNavigation: (String, NavOptionsBuilder.() -> Unit) -> Unit,
) {
    Column {
        if (state.model.isNotEmpty()) {
            LazyColumn {
                items(state.model) {
                    Note(it, onNavigation)
                    Divider(Modifier.padding(vertical = 2.dp))
                }
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "List is Empty",
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            onClick = { onNavigation(Screen.ADD_NOTE.name) {} },
            content = { Text(text = "Add Note") }
        )
    }
}


@Composable
private fun Note(note: NoteModel, onNavigation: (String, NavOptionsBuilder.() -> Unit) -> Unit) {
    Row(
        modifier = Modifier.clickable {
            println(note)
            onNavigation("${Screen.DETAILS_NOTE.name}/${note.id}") {}
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .weight(1f)
                .padding(vertical = 4.dp, horizontal = 4.dp)
        ) {
            Text(color = Color.White, text = note.title)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = note.text)
        }
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = note.date
        )
    }
}