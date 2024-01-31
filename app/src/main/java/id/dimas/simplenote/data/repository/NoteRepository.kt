package id.dimas.simplenote.data.repository

import androidx.lifecycle.LiveData
import id.dimas.simplenote.data.room.entity.NoteEntity
import id.dimas.simplenote.util.Result

interface NoteRepository {

    fun getAllNote(): LiveData<Result<List<NoteEntity>>>

    fun insertNote(noteEntity: NoteEntity): LiveData<Result<String>>

    fun updateNote(noteEntity: NoteEntity): LiveData<Result<Int>>

    fun deleteNote(noteEntity: NoteEntity): LiveData<Result<String>>

    fun getNoteById(id: Int?): LiveData<Result<NoteEntity>>
}