package id.dimas.simplenote.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import id.dimas.simplenote.R
import id.dimas.simplenote.adapter.NoteAdapter
import id.dimas.simplenote.data.room.entity.NoteEntity
import id.dimas.simplenote.util.Result
import id.dimas.simplenote.databinding.FragmentHomeBinding
import id.dimas.simplenote.ui.detail.DetailNoteFragment

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private val noteAdapter by lazy { NoteAdapter(::onNoteClicked) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNote()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvNote.adapter = noteAdapter
        binding.rvNote.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        observeNote()
    }

    private fun observeNote() {
        homeViewModel.getAllNote().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    if (result.data != null) {
                        noteAdapter.submitList(result.data)
                    }
                }

                is Result.Error -> {

                }


            }
        }
    }

    private fun addNote() {
        binding.fab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_nav_host, DetailNoteFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun onNoteClicked(note: NoteEntity) {
        val detailNoteFragment = DetailNoteFragment()
        val bundle = Bundle()
        bundle.putInt("noteId", note.id!!)
        detailNoteFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_nav_host, detailNoteFragment)
            .addToBackStack(null)
            .commit()


    }

}