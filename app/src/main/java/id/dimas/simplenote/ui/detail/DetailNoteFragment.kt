package id.dimas.simplenote.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.dimas.simplenote.data.room.entity.NoteEntity
import id.dimas.simplenote.databinding.FragmentDetailNoteBinding
import id.dimas.simplenote.util.Result

@AndroidEntryPoint
class DetailNoteFragment : Fragment() {

    private var _binding: FragmentDetailNoteBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailNoteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        addNote()
        updateNote()
        deleteNote()
        setupNote()
    }

    private fun addNote() {
        if (arguments?.getInt("noteId") == null) {
            binding.btnSave.setOnClickListener {

                val title = binding.lyNote.etTitleNote.text.toString()
                val note = binding.lyNote.etNote.text.toString()

                val noteEntity = NoteEntity(null, title, note)

                if (note.isNotBlank()) {
                    observeInsertNote(noteEntity)
                }
            }
        }

    }

    private fun updateNote() {
        if (arguments?.getInt("noteId") != null) {
            binding.btnDelete.isVisible = true
            binding.btnSave.setOnClickListener {
                val id = arguments?.getInt("noteId")
                val title = binding.lyNote.etTitleNote.text.toString()
                val note = binding.lyNote.etNote.text.toString()

                if (note.isNotBlank()) {
                    observeUpdateNote(NoteEntity(id, title, note))
                }

            }
        }
    }

    private fun deleteNote() {
        binding.btnDelete.setOnClickListener {
            val id = arguments?.getInt("noteId")
            val title = binding.lyNote.etTitleNote.text.toString()
            val note = binding.lyNote.etNote.text.toString()

            if (note.isNotBlank()) {
                observeDeleteNote(NoteEntity(id, title, note))
            }
        }
    }

    private fun observeInsertNote(noteEntity: NoteEntity) {

        detailViewModel.insertNote(noteEntity).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    clearView()
                    parentFragmentManager.popBackStack()
                }

                is Result.Error -> {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun observeUpdateNote(noteEntity: NoteEntity) {
        detailViewModel.updateNote(noteEntity).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    clearView()
                    parentFragmentManager.popBackStack()
                }

                is Result.Error -> {

                }

            }
        }
    }

    private fun observeDeleteNote(noteEntity: NoteEntity) {
        detailViewModel.deleteNote(noteEntity).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    clearView()
                    parentFragmentManager.popBackStack()
                }

                is Result.Error -> {

                }

            }
        }
    }


    private fun setupNote() {
        val noteId = arguments?.getInt("noteId")
        detailViewModel.getNoteById(noteId).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    if (result.data != null) {
                        binding.lyNote.apply {
                            etTitleNote.setText(result.data.title)
                            etNote.setText(result.data.note)
                        }
                    }
                }

                is Result.Error -> {

                }

            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.btnBack.setOnClickListener {
            clearView()
            parentFragmentManager.popBackStack()
        }
    }

    private fun clearView() {
        binding.lyNote.apply {
            etTitleNote.text?.clear()
            etNote.text?.clear()
        }
    }


}