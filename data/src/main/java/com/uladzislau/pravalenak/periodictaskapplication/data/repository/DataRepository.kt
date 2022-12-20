package com.uladzislau.pravalenak.periodictaskapplication.data.repository

import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    suspend fun getFieldById(id: Int): NoteEntity
    suspend fun getAllFields(): Flow<List<NoteEntity>>
    suspend fun addField(noteEntity: NoteEntity)
}