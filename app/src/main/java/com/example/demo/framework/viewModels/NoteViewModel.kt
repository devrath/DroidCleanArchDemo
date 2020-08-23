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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import com.example.demo.framework.dataSource.RoomNoteDataSource
import com.example.demo.framework.di.components.DaggerViewModelComponent
import com.example.demo.framework.di.modules.ApplicationModule
import com.example.demo.framework.useCases.NoteUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases : NoteUseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val saved = MutableLiveData<Boolean>()

    fun saveNote(note:Note){
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

}