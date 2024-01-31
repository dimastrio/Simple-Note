package id.dimas.simplenote.data.repositoryImpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import id.dimas.simplenote.data.repository.NoteRepository
import id.dimas.simplenote.data.room.AppDatabase
import id.dimas.simplenote.data.room.dao.NoteDao
import id.dimas.simplenote.data.room.entity.NoteEntity
import javax.inject.Inject
import id.dimas.simplenote.util.Result

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getAllNote(): LiveData<Result<List<NoteEntity>>> = liveData {
        emit(Result.Loading())
        try {

            emit(Result.Success(noteDao.getAllNote()))

        } catch (e: Exception) {
            emit(Result.Error("error"))
        }
    }

    override fun insertNote(noteEntity: NoteEntity): LiveData<Result<String>> = liveData {
        emit(Result.Loading())
        try {

            noteDao.insertNote(noteEntity)

            emit(Result.Success("Success"))

        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun updateNote(noteEntity: NoteEntity): LiveData<Result<Int>> = liveData {
        emit(Result.Loading())
        try {

            val result = noteDao.updateNote(noteEntity)

            emit(Result.Success(result))

        } catch (e: Exception) {
            emit(Result.Error("error"))
        }
    }

    override fun deleteNote(noteEntity: NoteEntity): LiveData<Result<String>> = liveData {
        emit(Result.Loading())
        try {

            noteDao.deleteNote(noteEntity)
            emit(Result.Success("Success"))

        } catch (e: Exception) {
            emit(Result.Error("error"))
        }
    }

    override fun getNoteById(id: Int?): LiveData<Result<NoteEntity>> = liveData {
        emit(Result.Loading())
        try {

            emit(Result.Success(noteDao.getNoteById(id!!)))

        } catch (e: Exception) {
            emit(Result.Error("error"))
        }
    }
}