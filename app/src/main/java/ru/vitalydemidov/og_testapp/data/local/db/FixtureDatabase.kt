package ru.vitalydemidov.og_testapp.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.vitalydemidov.og_testapp.data.model.Fixture

@Database(
    entities = [
        Fixture::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FixtureDatabase : RoomDatabase() {

    abstract fun fixturesDao(): FixturesDao

    companion object {
        internal const val DATABASE_NAME = "fixtures_database"
        internal const val FIXTURES_TABLE_NAME = "fixtures"
    }
}