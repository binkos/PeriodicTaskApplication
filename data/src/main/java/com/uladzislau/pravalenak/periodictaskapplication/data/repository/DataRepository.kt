package com.uladzislau.pravalenak.periodictaskapplication.data.repository

import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity

interface DataRepository {

    suspend fun getFieldById(id: Long): NoteEntity
    suspend fun getAllFields(): List<NoteEntity>
}