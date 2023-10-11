package com.dwainekenney.imagenotes.di

import android.content.Context
import android.content.SharedPreferences
import com.dwainekenney.imagenotes.data.storage.SharedPreferencesNotesStorage
import com.dwainekenney.imagenotes.domain.storage.NotesStorage
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotesModule {

    @Singleton
    @Binds
    abstract fun bindNotesStorage(sharedPreferencesNotesStorage: SharedPreferencesNotesStorage): NotesStorage

    companion object {

        @Provides
        @NotesSharedPrefs
        @Singleton
        fun provideNotesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
            context.getSharedPreferences("notes", Context.MODE_PRIVATE)
    }
}