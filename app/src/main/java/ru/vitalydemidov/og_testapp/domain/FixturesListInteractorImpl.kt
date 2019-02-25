package ru.vitalydemidov.og_testapp.domain

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import ru.vitalydemidov.og_testapp.base.model.BaseItem
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.model.Competition

internal class FixturesListInteractorImpl(
    private val fixturesRepository: FixturesDataSource,
    private val fixtureMapper: FixtureMapper
) : FixturesListInteractor {

    override fun getFixturesList(filter: FixturesFilter): Flowable<FixturesResult> {
        return Flowable.combineLatest(
            fixturesRepository.getFixtures(filter)
                .observeOn(Schedulers.computation())
                .map { fixtures -> fixtureMapper.applyToList(fixtures) },
            fixturesRepository.getAllCompetitions(),
            BiFunction<List<BaseItem<*>>, List<Competition>, FixturesResult> { t1, t2 -> FixturesResult(t1, t2) }
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}