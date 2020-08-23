package com.example.demo.framework.di.modules

import android.app.Application
import com.example.core.repoLayer.repository.NoteRepository
import com.example.demo.framework.dataSource.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesRepository(app : Application) = NoteRepository(RoomNoteDataSource(app))
}