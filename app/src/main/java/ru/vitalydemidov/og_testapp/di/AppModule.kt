package ru.vitalydemidov.og_testapp.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val appContext: Context) {

    @Provides
    @PerAppScope
    fun provideAppContext(): Context = appContext

}