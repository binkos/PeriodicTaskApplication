package com.uladzislau.pravalenak.periodictaskapplication.screens.add

import androidx.compose.runtime.Stable

@Stable
data class AddNoteState(
    val title: String = "",
    val description: String = ""
)