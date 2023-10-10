package com.dwainekenney.imagenotes.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @SettingsSharedPrefs
    @Provides
    fun provideDefaultSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
}