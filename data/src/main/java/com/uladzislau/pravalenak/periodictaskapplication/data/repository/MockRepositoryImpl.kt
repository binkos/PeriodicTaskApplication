package com.uladzislau.pravalenak.periodictaskapplication.data.repository

import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class MockRepositoryImpl : DataRepository {

    private val notes: MutableStateFlow<List<NoteEntity>> = MutableStateFlow(emptyList())

    override suspend fun getFieldById(id: Int): NoteEntity =
        notes.value.first { it.id == id }

    override suspend fun getAllFields(): Flow<List<NoteEntity>> = notes

    override suspend fun addField(noteEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            notes.value
                .toMutableList()
                .also {
                    val newNote = noteEntity.copy(id = notes.value.size)
                    it.add(newNote)
                    notes.emit(it)
                }
        }
    }
}