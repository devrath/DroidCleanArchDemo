package com.example.demo.framework.dataSource

import android.content.Context
import com.example.core.data.Note
import com.example.core.repoLayer.dataSource.NoteDataSource
import com.example.demo.framework.db.DatabaseService
import com.example.demo.framework.db.NoteEntity

/**
 * Input : note object is obtained in the form of core and converted into entity note
 */

class RoomNoteDataSource(context: Context) : NoteDataSource {

    val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}