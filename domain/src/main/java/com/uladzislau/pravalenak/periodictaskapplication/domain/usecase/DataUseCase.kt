package com.uladzislau.pravalenak.periodictaskapplication.domain.usecase

import com.uladzislau.pravalenak.periodictaskapplication.domain.model.NewNoteModel
import com.uladzislau.pravalenak.periodictaskapplication.domain.model.NoteModelWithId
import kotlinx.coroutines.flow.Flow

interface DataUseCase {

    suspend fun addNote(noteModel: NewNoteModel)
    suspend fun getNoteById(id: Int): NoteModelWithId
    suspend fun getNoteModels(): Flow<List<NoteModelWithId>>
}