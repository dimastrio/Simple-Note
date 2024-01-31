package id.dimas.simplenote.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.dimas.simplenote.data.room.dao.NoteDao
import id.dimas.simplenote.data.room.entity.NoteEntity


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}