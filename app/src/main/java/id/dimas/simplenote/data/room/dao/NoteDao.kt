package id.dimas.simplenote.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.dimas.simplenote.data.room.entity.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM tb_note")
    suspend fun getAllNote(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity): Int

    @Query("SELECT * FROM tb_note WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

}