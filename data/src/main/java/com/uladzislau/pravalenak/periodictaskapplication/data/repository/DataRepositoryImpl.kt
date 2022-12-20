package com.uladzislau.pravalenak.periodictaskapplication.data.repository

import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDB
import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDao
import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DataRepositoryImpl(
    private val dao: NoteDao
) : DataRepository {

    override suspend fun getFieldById(id: Int): NoteEntity =
        withContext(Dispatchers.Default) {
            dao.getNoteById(id)
        }

    override suspend fun getAllFields(): Flow<List<NoteEntity>> =
        withContext(Dispatchers.Default) {
            dao.getAllNotes()
        }

    override suspend fun addField(noteEntity: NoteEntity) {
        withContext(Dispatchers.Default) {
            dao.addNote(noteEntity)
        }
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
}