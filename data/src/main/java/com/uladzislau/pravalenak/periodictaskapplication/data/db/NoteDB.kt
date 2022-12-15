package com.uladzislau.pravalenak.periodictaskapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.DateConverter
import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity

@Database(version = 1, entities = [NoteEntity::class])
@TypeConverters(DateConverter::class)
abstract class NoteDB : RoomDatabase() {
    abstract fun userDao(): NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NoteDB? = null

        fun getDatabase(context: Context): NoteDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, NoteDB::class.java, "note")
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}