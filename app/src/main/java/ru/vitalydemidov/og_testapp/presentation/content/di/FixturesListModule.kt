package ru.vitalydemidov.og_testapp.presentation.content.di

import dagger.Module
import dagger.Provides
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.di.Repository
import ru.vitalydemidov.og_testapp.domain.FixturesListInteractor
import ru.vitalydemidov.og_testapp.domain.FixturesListInteractorImpl
import ru.vitalydemidov.og_testapp.presentation.content.FixturesListAdapter
import ru.vitalydemidov.og_testapp.presentation.content.FixturesListContract
import ru.vitalydemidov.og_testapp.presentation.content.FixturesListPresenter
import ru.vitalydemidov.og_testapp.util.FixtureType

@Module
internal class FixturesListModule {

    @Provides
    @PerFragmentScope
    internal fun provideFixturesListPresenter(
        fixturesListInteractor: FixturesListInteractor,
        fixtureType: FixtureType
    ): FixturesListContract.Presenter = FixturesListPresenter(fixturesListInteractor, fixtureType)

    @Provides
    @PerFragmentScope
    internal fun provideFixturesListInteractor(
        @Repository fixturesRepository: FixturesDataSource
    ): FixturesListInteractor = FixturesListInteractorImpl(fixturesRepository)

    @Provides
    @PerFragmentScope
    internal fun provideFixturesListAdapter() = FixturesListAdapter()

}