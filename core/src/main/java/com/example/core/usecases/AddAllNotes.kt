package com.example.core.usecases

import com.example.core.repoLayer.repository.NoteRepository

class AddAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}