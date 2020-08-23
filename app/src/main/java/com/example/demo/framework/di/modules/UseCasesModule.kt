package com.example.demo.framework.di.modules

import com.example.core.repoLayer.repository.NoteRepository
import com.example.core.usecases.AddNote
import com.example.core.usecases.GetAllNotes
import com.example.core.usecases.GetNote
import com.example.core.usecases.RemoveNote
import com.example.demo.framework.useCases.NoteUseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun providesUseCases(repository: NoteRepository) = NoteUseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository)
    )
}