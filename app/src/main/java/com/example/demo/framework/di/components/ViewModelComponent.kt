package com.example.demo.framework.di.components

import com.example.demo.framework.di.modules.ApplicationModule
import com.example.demo.framework.di.modules.RepositoryModule
import com.example.demo.framework.di.modules.UseCasesModule
import com.example.demo.framework.viewModels.ListViewModel
import com.example.demo.framework.viewModels.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class,RepositoryModule::class,UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}