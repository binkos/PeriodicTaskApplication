package com.uladzislau.pravalenak.periodictaskapplication.domain.model

import java.util.*

sealed class NoteModel(val title: String, val description: String, val date: Date)

class NoteModelWithId(val id: Int, title: String, description: String, date: Date) :
    NoteModel(title, description, date)

class NewNoteModel(title: String, description: String, date: Date) :
    NoteModel(title, description, date)