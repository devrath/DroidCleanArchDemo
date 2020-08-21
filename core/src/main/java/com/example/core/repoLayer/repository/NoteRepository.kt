package com.example.core.repoLayer.repository

import com.example.core.data.Note
import com.example.core.repoLayer.dataSource.NoteDataSource

class NoteRepository(private val dataSource: NoteDataSource) {
    suspend fun addNote(note : Note) = dataSource.add(note)
    suspend fun getNote(id : Long) = dataSource.get(id)
    suspend fun getAllNotes() = dataSource.getAll()
    suspend fun removeNote(note: Note) = dataSource.remove(note)
}