package com.example.madlevel5task1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.madlevel5task1.dao.NoteDao
import com.example.madlevel5task1.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "NOTE_DATABASE"

        @Volatile
        private var noteRoomDatabaseInstance: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase? {
            if (noteRoomDatabaseInstance == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    if (noteRoomDatabaseInstance == null) {
                        noteRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            NoteRoomDatabase::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return noteRoomDatabaseInstance
        }
    }
}