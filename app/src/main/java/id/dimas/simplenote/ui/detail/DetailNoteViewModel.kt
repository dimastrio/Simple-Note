package id.dimas.simplenote.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.dimas.simplenote.data.repository.NoteRepository
import id.dimas.simplenote.data.room.entity.NoteEntity
import javax.inject.Inject

@HiltViewModel
class DetailNoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    fun insertNote(noteEntity: NoteEntity) = repository.insertNote(noteEntity)

    fun updateNote(noteEntity: NoteEntity) = repository.updateNote(noteEntity)

    fun deleteNote(noteEntity: NoteEntity) = repository.deleteNote(noteEntity)

    fun getNoteById(id: Int?) = repository.getNoteById(id)

}