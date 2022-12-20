package com.uladzislau.pravalenak.periodictaskapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: NoteEntity)

    @Update(entity = NoteEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun removeNote(note: NoteEntity)

    @Query("Select * from note")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query(
        """
        Select *
        from note
        where id=:id
    """
    )
    suspend fun getNoteById(id: Int): NoteEntity
}