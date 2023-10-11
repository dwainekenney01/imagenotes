package com.dwainekenney.imagenotes.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SettingsSharedPrefs

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NotesSharedPrefs
