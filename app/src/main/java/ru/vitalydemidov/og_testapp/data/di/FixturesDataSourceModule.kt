package ru.vitalydemidov.og_testapp.data.di

import dagger.Module
import dagger.Provides
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.FixturesRepository
import ru.vitalydemidov.og_testapp.data.local.FixturesLocalDataSource
import ru.vitalydemidov.og_testapp.data.local.Local
import ru.vitalydemidov.og_testapp.data.remote.FixturesApi
import ru.vitalydemidov.og_testapp.data.remote.FixturesRemoteDataSource
import ru.vitalydemidov.og_testapp.data.remote.di.Remote

@Module
internal class FixturesDataSourceModule {

    @Provides
    @FixturesDataSourceScope
    @Repository
    internal fun provideFixtureRepository(
        @Local localDataSource: FixturesDataSource,
        @Remote remoteDataSource: FixturesDataSource
    ): FixturesDataSource =
        FixturesRepository(localDataSource, remoteDataSource)

    @Provides
    @FixturesDataSourceScope
    @Remote
    internal fun provideFixtureRemoteDataSource(fixturesApi: FixturesApi): FixturesDataSource =
        FixturesRemoteDataSource(fixturesApi)

    @Provides
    @FixturesDataSourceScope
    @Local
    internal fun provideFixtureLocalDataSource(): FixturesDataSource =
        FixturesLocalDataSource()

}