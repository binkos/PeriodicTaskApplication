package com.uladzislau.pravalenak.periodictaskapplication.domain.usecase

import com.uladzislau.pravalenak.periodictaskapplication.domain.model.NewNoteModel
import com.uladzislau.pravalenak.periodictaskapplication.domain.model.NoteModelWithId
import com.uladzislau.pravalenak.periodictaskapplication.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class DataUseCaseImpl(private val repository: DataRepository) : DataUseCase {

    override suspend fun addNote(noteModel: NewNoteModel) {
        repository.addField(noteModel)
    }

    override suspend fun getNoteById(id: Int): NoteModelWithId = repository.getFieldById(id)

    override suspend fun getNoteModels(): Flow<List<NoteModelWithId>> =
        repository.getAllFields()
}