package com.example.demo.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.databinding.FragmentListBinding
import com.example.demo.framework.viewModels.ListViewModel
import com.example.demo.presentation.adapters.NotesListAdapter


/**
 * A simple [Fragment] subclass.
 * Displaying the list of notes
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val noteListAdapter = NotesListAdapter(arrayListOf())
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel  = ViewModelProviders.of(this).get(ListViewModel::class.java)
        binding.noteListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }
        binding.addNote.setOnClickListener {
            goToNoteDetails()
        }
        viewModel.listNotes.observe(viewLifecycleOwner, Observer {
            binding.loadingView.visibility = View.GONE
            binding.noteListView.visibility  = View.VISIBLE
            noteListAdapter.updateNotes(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.displayNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToNoteDetails(id : Long = 0L){
        val action = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(binding.rootId).navigate(action)
    }
}