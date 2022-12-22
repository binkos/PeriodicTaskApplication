package com.uladzislau.pravalenak.periodictaskapplication.data.repository

import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDao
import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity
import com.uladzislau.pravalenak.periodictaskapplication.domain.model.NewNoteModel
import com.uladzislau.pravalenak.periodictaskapplication.domain.model.NoteModelWithId
import com.uladzislau.pravalenak.periodictaskapplication.domain.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DataRepositoryImpl(
    private val dao: NoteDao
) : DataRepository {

    override suspend fun getFieldById(id: Int): NoteModelWithId =
        withContext(Dispatchers.Default) {
            val entity = dao.getNoteById(id)
            NoteModelWithId(entity.id ?: 0, entity.title, entity.text, entity.date)
        }

    override suspend fun addField(noteEntity: NewNoteModel) {
        withContext(Dispatchers.Default) {
            NoteEntity(null, noteEntity.title, noteEntity.description, noteEntity.date)
                .also { entity -> dao.addNote(entity) }
        }
    }

    override suspend fun getAllFields(): Flow<List<NoteModelWithId>> =
        withContext(Dispatchers.Default) {
            dao.getAllNotes().map { it.matToModels() }
        }

    companion object {
        private var INSTANCE: DataRepository? = null

        fun createRepository(dao: NoteDao): DataRepository {
            return if (INSTANCE == null) {
                val repository = DataRepositoryImpl(dao)
                INSTANCE = repository

                repository
            } else requireNotNull(INSTANCE)
        }
    }

    private fun List<NoteEntity>.matToModels(): List<NoteModelWithId> =
        map { NoteModelWithId(it.id ?: 0, it.title, it.text, it.date) }
}