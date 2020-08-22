package com.example.demo.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.core.data.Note
import com.example.demo.R
import com.example.demo.databinding.FragmentListBinding
import com.example.demo.databinding.FragmentNoteBinding
import com.example.demo.framework.viewModels.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*


/**
 * A simple [Fragment] subclass.
 * Display Note
 */
class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : NoteViewModel

    private var currentNote = Note(title = "",content = "",creationTime = 0L,updateTime = 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        setObservers()
        binding.addNote.setOnClickListener {
            if(titleView.text.toString().isNotEmpty() && contentView.text.toString().isNotEmpty()){
                val time : Long = System.currentTimeMillis()
                currentNote.title = titleView.text.toString()
                currentNote.content = contentView.text.toString()
                currentNote.updateTime = time
                if(currentNote.id == 0L){
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            }else{
                Toast.makeText(activity,"Enter data",Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun setObservers() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity,"Note Added",Toast.LENGTH_LONG).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}