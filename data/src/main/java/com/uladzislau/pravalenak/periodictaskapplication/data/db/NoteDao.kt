package com.uladzislau.pravalenak.periodictaskapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: NoteEntity)

    @Update(entity = NoteEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: NoteEntity)

    @Delete
    fun removeNote(note: NoteEntity)

    @Query("Select * from note")
    suspend fun getAllNotes(): List<NoteEntity>
}