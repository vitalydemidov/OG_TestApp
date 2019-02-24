package ru.vitalydemidov.og_testapp.data.local.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.vitalydemidov.og_testapp.data.local.db.FixtureDatabase
import ru.vitalydemidov.og_testapp.data.local.db.FixtureDatabase.Companion.DATABASE_NAME
import ru.vitalydemidov.og_testapp.di.PerAppScope

@Module
internal class DatabaseModule {

    @Provides
    @PerAppScope
    internal fun provideFixturesDatabase(appContext: Context): FixtureDatabase =
        Room.databaseBuilder(appContext, FixtureDatabase::class.java, DATABASE_NAME).build()

}