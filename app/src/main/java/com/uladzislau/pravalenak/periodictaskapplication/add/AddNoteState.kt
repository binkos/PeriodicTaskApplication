package com.uladzislau.pravalenak.periodictaskapplication.add

import androidx.compose.runtime.Stable

@Stable
data class AddNoteState(
    val title: String = "",
    val description: String = ""
)