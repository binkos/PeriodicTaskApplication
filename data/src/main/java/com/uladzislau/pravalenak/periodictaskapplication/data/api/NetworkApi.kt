package com.uladzislau.pravalenak.periodictaskapplication.data.api

import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity

interface NetworkApi {
    suspend fun uploadNote(noteEntity: NoteEntity)
}