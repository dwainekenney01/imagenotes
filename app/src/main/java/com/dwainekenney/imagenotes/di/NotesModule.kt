package com.dwainekenney.imagenotes.di

import com.dwainekenney.imagenotes.data.storage.RealmNotesStorage
import com.dwainekenney.imagenotes.domain.storage.NotesStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotesModule {

    @Singleton
    @Binds
    abstract fun bindNotesStorage(realmNotesStorage: RealmNotesStorage): NotesStorage
}