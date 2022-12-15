package com.uladzislau.pravalenak.periodictaskapplication.data.repository

import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDao
import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class DataRepositoryImpl(
    private val dao: NoteDao
) : DataRepository {

    override suspend fun getFieldById(id: Long): NoteEntity {
        return NoteEntity(0, "", "", Date())
    }

    override suspend fun getAllFields(): List<NoteEntity> {
        return withContext(Dispatchers.Default) { dao.getAllNotes() }
    }
}