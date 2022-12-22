package com.uladzislau.pravalenak.periodictaskapplication.screens.main

import androidx.compose.runtime.Stable
import com.uladzislau.pravalenak.periodictaskapplication.core.compose.StableList
import com.uladzislau.pravalenak.periodictaskapplication.models.NoteModel

@Stable
data class AllNotesState(
    val model: StableList<NoteModel> = StableList(emptyList())
)