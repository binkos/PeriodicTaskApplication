package com.uladzislau.pravalenak.periodictaskapplication.models

import androidx.compose.runtime.Stable

@Stable
data class NoteModel(
    val id: Int = DEFAULT_NEGATIVE_ID,
    val title: String = "",
    val text: String = "",
    val date: String = ""
)

private const val DEFAULT_NEGATIVE_ID = -1