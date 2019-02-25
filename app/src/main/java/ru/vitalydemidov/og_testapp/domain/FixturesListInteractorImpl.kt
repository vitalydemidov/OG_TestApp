package ru.vitalydemidov.og_testapp.domain

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.vitalydemidov.og_testapp.data.FixturesDataSource

internal class FixturesListInteractorImpl(
    private val fixturesRepository: FixturesDataSource,
    private val fixtureMapper: FixtureMapper
) : FixturesListInteractor {

    override fun getFixturesList(filter: FixturesFilter): Flowable<FixturesResult> {
        return fixturesRepository.getFixtures(filter)
            .map { fixtures -> fixtureMapper.applyToList(fixtures) }
            .flatMap(
                { fixturesRepository.getAllCompetitions() },
                { fixtures, competitions -> FixturesResult(fixtures, competitions) }
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}