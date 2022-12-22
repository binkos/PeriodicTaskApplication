package com.uladzislau.pravalenak.periodictaskapplication.domain.repository

import com.uladzislau.pravalenak.periodictaskapplication.domain.model.NewNoteModel
import com.uladzislau.pravalenak.periodictaskapplication.domain.model.NoteModelWithId
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun getFieldById(id: Int): NoteModelWithId
    suspend fun getAllFields(): Flow<List<NoteModelWithId>>
    suspend fun addField(noteEntity: NewNoteModel)
}