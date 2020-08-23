package com.example.demo.framework.useCases

import com.example.core.usecases.AddNote
import com.example.core.usecases.GetAllNotes
import com.example.core.usecases.GetNote
import com.example.core.usecases.RemoveNote

data class NoteUseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)