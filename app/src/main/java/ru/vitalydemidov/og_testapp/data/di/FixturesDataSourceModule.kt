package ru.vitalydemidov.og_testapp.data.di

import dagger.Module
import dagger.Provides
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.FixturesRepository
import ru.vitalydemidov.og_testapp.data.local.FixturesLocalDataSource
import ru.vitalydemidov.og_testapp.data.local.di.Local
import ru.vitalydemidov.og_testapp.data.local.db.FixtureDatabase
import ru.vitalydemidov.og_testapp.data.remote.api.FixturesApi
import ru.vitalydemidov.og_testapp.data.remote.FixturesRemoteDataSource
import ru.vitalydemidov.og_testapp.data.remote.di.Remote
import ru.vitalydemidov.og_testapp.di.PerAppScope

@Module
internal class FixturesDataSourceModule {

    @Provides
    @PerAppScope
    @Repository
    internal fun provideFixtureRepository(
        @Local localDataSource: FixturesDataSource,
        @Remote remoteDataSource: FixturesDataSource
    ): FixturesDataSource =
        FixturesRepository(localDataSource, remoteDataSource)

    @Provides
    @PerAppScope
    @Remote
    internal fun provideFixtureRemoteDataSource(fixturesApi: FixturesApi): FixturesDataSource =
        FixturesRemoteDataSource(fixturesApi)

    @Provides
    @PerAppScope
    @Local
    internal fun provideFixtureLocalDataSource(fixturesDatabase: FixtureDatabase): FixturesDataSource =
        FixturesLocalDataSource(fixturesDatabase)

}