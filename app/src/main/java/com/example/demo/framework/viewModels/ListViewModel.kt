package com.example.demo.framework.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.core.data.Note
import com.example.demo.framework.di.components.DaggerViewModelComponent
import com.example.demo.framework.di.modules.ApplicationModule
import com.example.demo.framework.useCases.NoteUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var useCases : NoteUseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }



    private val coroutineScope = CoroutineScope(Dispatchers.IO)

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