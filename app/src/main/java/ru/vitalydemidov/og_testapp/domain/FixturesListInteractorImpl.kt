package ru.vitalydemidov.og_testapp.domain

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.vitalydemidov.og_testapp.base.model.BaseItem
import ru.vitalydemidov.og_testapp.data.FixturesDataSource

internal class FixturesListInteractorImpl(
    private val fixturesRepository: FixturesDataSource,
    private val fixtureMapper: FixtureMapper
) : FixturesListInteractor {

    override fun getFixturesList(filter: FixturesFilter): Flowable<List<BaseItem<in Nothing>>> {
        return fixturesRepository.getFixtures(filter)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { fixtures -> fixtureMapper.applyToList(fixtures) }
            .observeOn(AndroidSchedulers.mainThread())
    }

}