package com.example.demo.framework.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.core.data.Note
import com.example.core.repoLayer.repository.NoteRepository
import com.example.core.usecases.AddNote
import com.example.core.usecases.GetAllNotes
import com.example.core.usecases.GetNote
import com.example.core.usecases.RemoveNote
import com.example.demo.framework.dataSource.RoomNoteDataSource
import com.example.demo.framework.useCases.NoteUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = NoteUseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository)
    )

    val dataDisplayed = MutableLiveData<Boolean>()
    val listNotes = MutableLiveData<List<Note>>()


    fun displayNotes(){
        coroutineScope.launch {
            val listOfNotes = useCases.getAllNotes.invoke()
            listNotes.postValue(listOfNotes)
            dataDisplayed.postValue(true)
        }
    }

}