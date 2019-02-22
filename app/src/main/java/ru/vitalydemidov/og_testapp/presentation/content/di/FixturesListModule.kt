package ru.vitalydemidov.og_testapp.presentation.content.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.vitalydemidov.og_testapp.base.mapper.BaseItemMapper
import ru.vitalydemidov.og_testapp.base.adapter.BaseDelegateAdapter
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.di.Repository
import ru.vitalydemidov.og_testapp.domain.FixtureMapper
import ru.vitalydemidov.og_testapp.domain.FixturesListInteractor
import ru.vitalydemidov.og_testapp.domain.FixturesListInteractorImpl
import ru.vitalydemidov.og_testapp.presentation.content.FixturesListContract
import ru.vitalydemidov.og_testapp.presentation.content.FixturesListPresenter
import ru.vitalydemidov.og_testapp.presentation.content.adapter.FixturesFinalListAdapter
import ru.vitalydemidov.og_testapp.presentation.content.adapter.FixturesUpcomingListAdapter
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
        @Repository fixturesRepository: FixturesDataSource,
        fixtureMapper: FixtureMapper
    ): FixturesListInteractor = FixturesListInteractorImpl(fixturesRepository, fixtureMapper)

    @Provides
    @PerFragmentScope
    internal fun provideFixtureMapper(
        context: Context,
        baseItemMapper: BaseItemMapper
    ): FixtureMapper = FixtureMapper(context, baseItemMapper)

    @Provides
    @PerFragmentScope
    internal fun provideFixturesListAdapter(fixtureType: FixtureType): BaseDelegateAdapter<in Nothing> =
        when (fixtureType) {
            FixtureType.UPCOMING -> FixturesUpcomingListAdapter()
            FixtureType.FINAL -> FixturesFinalListAdapter()
        }

}